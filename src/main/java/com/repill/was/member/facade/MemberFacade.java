package com.repill.was.member.facade;

import com.repill.was.global.enums.ItemType;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.factory.itemvalidate.ItemValidateFactory;
import com.repill.was.global.factory.itemvalidate.ItemValidator;
import com.repill.was.global.model.EntityListData;
import com.repill.was.global.utils.PageUtils;
import com.repill.was.global.utils.TimeUtils;
import com.repill.was.member.controller.command.*;
import com.repill.was.member.controller.dto.request.CloseAccountRequest;
import com.repill.was.member.controller.dto.request.MemberLogoutRequest;
import com.repill.was.member.controller.dto.response.MemberDetailProfileResponse;
import com.repill.was.member.controller.dto.response.MemberFollowerResponse;
import com.repill.was.member.controller.dto.response.RecentlyViewedItemResponse;
import com.repill.was.member.controller.dto.response.view.MemberView;
import com.repill.was.member.controller.dto.view.MemberBlockListView;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountNotFoundException;
import com.repill.was.member.entity.account.AccountRepository;
import com.repill.was.member.entity.member.*;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberblock.MemberBlock;
import com.repill.was.member.entity.memberblock.MemberBlockRepository;
import com.repill.was.member.entity.memberfollwer.MemberFollower;
import com.repill.was.member.entity.memberfollwer.MemberFollowerFoundException;
import com.repill.was.member.entity.memberfollwer.MemberFollowerRepository;
import com.repill.was.member.query.*;
import com.repill.was.member.query.vo.RecentlyViewedItemInfoVO;
import com.repill.was.member.service.AccountService;
import com.repill.was.member.service.MemberBlockService;
import com.repill.was.member.service.MemberLikeService;
import com.repill.was.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import net.logstash.logback.util.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.repill.was.global.enums.Sort.CREATED_AT;
import static com.repill.was.global.enums.Sort.MEMBER_ID;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberQueries memberQueries;
    private final ItemValidateFactory itemValidateFactory;
    private final MemberLikeService memberLikeService;
    private final MemberLikeQueries memberLikeQueries;
    private final MemberService memberService;
    private final MemberFollowerQueries memberFollowerQueries;
    private final MemberBlockService memberBlockService;
    private final MemberBlockQueries memberBlockQueries;

    public void closeAccount(AccountId accountId, CloseAccountRequest request) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(BadRequestException::new);
        memberService.proceedClosingAccount(member, MemberDeleteCommand.request(request.getType(), request.getAdditionalInformation()));
    }

    public int addLike(AccountId accountId, String likeType, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        memberLikeService.addLike(MemberLikeCommand.request(
                member.getId(),
                likeType,
                itemId
        ));
        return memberLikeQueries.findByMemberNickName(MemberLikeCommand.request(
                member.getId(),
                likeType,
                itemId));
    }

    public int deleteLike(AccountId accountId, String likeType, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        memberLikeService.deleteLike(MemberLikeCommand.request(
                member.getId(),
                likeType,
                itemId
        ));
        return memberLikeQueries.findByMemberNickName(MemberLikeCommand.request(
                member.getId(),
                likeType,
                itemId));
    }

    public MemberBlockListView blockMember(AccountId accountId, MemberId blockMemberId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        if(member.getId().equals(blockMemberId)) throw new BadRequestException("본인 차단 안됨");
        memberBlockService.blockMemberIfNeeded(member.getId(), blockMemberId);
        return new MemberBlockListView(memberBlockQueries.getAllMemberBlockList(member.getId()).stream()
                .map(memberBlock -> memberBlock.getTargetMemberId().getId()).collect(Collectors.toList()));
    }

    public MemberBlockListView unblockMember(AccountId accountId, MemberId blockMemberId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        if(member.getId().equals(blockMemberId)) throw new BadRequestException("본인 차단 해제 안됨");
        memberBlockService.unblockMemberIfNeeded(member.getId(), blockMemberId);
        return new MemberBlockListView(memberBlockQueries.getAllMemberBlockList(member.getId()).stream()
                .map(memberBlock -> memberBlock.getTargetMemberId().getId()).collect(Collectors.toList()));
    }

    public MemberDetailProfileResponse getMyProfile(AccountId accountId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        MemberId memberIdQuery = member.getId();
        return memberQueries.findMyProfileByMemberId(memberIdQuery);
    }

    public Boolean checkDuplicateNickname(MemberNickNameDuplicatedCheckCommand memberNickNameDuplicatedCheckCommand) {
        if(memberNickNameDuplicatedCheckCommand.isUseKakaoNickname()) {
            return true;
        }
        return memberQueries.findByMemberNickName(memberNickNameDuplicatedCheckCommand.getCheckNickName()).isEmpty();
    }

    //todo Pagenation 리펙토링 필요
    @Transactional(readOnly = true)
    public Page<RecentlyViewedItemResponse> getRecentlyViewItems(AccountId accountId, ItemType itemType, int size, int page) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        List<RecentlyViewedItem> recentlyViewedItems = member.getRecentlyViewedItems();
        List<RecentlyViewedItemResponse> list = recentlyViewedItems.parallelStream()
                .filter(one -> one.getItemType().equals(itemType))
                .map(one -> {
            return RecentlyViewedItemResponse.from(one, itemValidateFactory);
        }).collect(Collectors.toList());
        return PageUtils.makePage(list, CREATED_AT.name(), size, page);
    }

    @Transactional
    public void addRecentlyView(ItemType itemType, AccountId accountId, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        validatorBy.addRecentlyViewedItem(member, itemId);
    }

    @Transactional
    public void deleteRecentlyView(ItemType itemType, AccountId accountId, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        validatorBy.deleteRecentlyViewedItem(member, itemId);
    }


    //todo Pagenation 리펙토링 필요
    @Transactional(readOnly = true)
    public Page<RecentlyViewedItemResponse> getFavoriteItems(AccountId accountId, ItemType itemType, int size, int page) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        List<FavoriteItem> favoriteItems = member.getFavoriteItems();
        List<RecentlyViewedItemResponse> list = favoriteItems.parallelStream()
                .filter(one -> one.getItemType().equals(itemType))
                .map(one -> {
            return RecentlyViewedItemResponse.from(one, itemValidateFactory);
        }).collect(Collectors.toList());
        return PageUtils.makePage(list, CREATED_AT.name(), size, page);
    }

    @Transactional
    public void addFavoriteItem(ItemType itemType, AccountId accountId, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        validatorBy.addFavoriteItem(member, itemId);
    }

    @Transactional
    public void deleteFavoriteItem(ItemType itemType, AccountId accountId, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        validatorBy.deleteFavoriteItem(member, itemId);
    }

    //todo Pagenation 리펙토링 필요
    @Transactional(readOnly = true)
    public Page<MemberFollowerResponse> getFollowers(AccountId accountId, int size, int page) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        List<MemberFollower> memberFollowers = memberFollowerQueries.findByMemberId(member.getId());
        List<MemberFollowerResponse> list = memberFollowers.parallelStream().map(one -> {
            Member followeredMember = memberQueries.findById(one.getFollowerId()).orElseThrow(MemberNotFoundException::new);
            MemberView memberView = MemberView.newOne(followeredMember.getId().getId(), followeredMember.getNickname(), followeredMember.getImageSrc());
           return MemberFollowerResponse.newOne(
                   memberView,
                   TimeUtils.convertToISO_8061(one.getCreatedAt()),
                   memberFollowerQueries.findFolloweredByMemberIdAndFollowerId(one.getFollowerId(), member.getId()).isPresent());
        }).collect(Collectors.toList());
        return PageUtils.makePage(list, CREATED_AT.name(), size, page);
    }

    @Transactional(readOnly = true)
    public Page<MemberFollowerResponse> getFollowered(AccountId accountId, int size, int page) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        List<MemberFollower> memberFollowers = memberFollowerQueries.findFolloweredByMemberId(member.getId());
        List<MemberFollowerResponse> list = memberFollowers.parallelStream().map(one -> {
            Member followeredMember = memberQueries.findById(one.getMemberId()).orElseThrow(MemberNotFoundException::new);
            MemberView memberView = MemberView.newOne(followeredMember.getId().getId(), followeredMember.getNickname(), followeredMember.getImageSrc());
            return MemberFollowerResponse.newOne(
                    memberView,
                    TimeUtils.convertToISO_8061(one.getCreatedAt()),
                    memberFollowerQueries.findFolloweredByMemberIdAndFollowerId(member.getId(), one.getMemberId()).isPresent());
        }).collect(Collectors.toList());
        return PageUtils.makePage(list, CREATED_AT.name(), size, page);
    }
}
