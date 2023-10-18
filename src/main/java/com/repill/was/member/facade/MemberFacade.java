package com.repill.was.member.facade;

import com.repill.was.global.enums.ItemType;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.factory.itemvalidate.ItemValidateFactory;
import com.repill.was.global.factory.itemvalidate.ItemValidator;
import com.repill.was.global.model.ImageListData;
import com.repill.was.global.response.CommonResponse;
import com.repill.was.global.utils.PageUtils;
import com.repill.was.item.query.vo.ItemVO;
import com.repill.was.member.controller.command.*;
import com.repill.was.member.controller.dto.request.CloseAccountRequest;
import com.repill.was.member.controller.dto.request.MemberLogoutRequest;
import com.repill.was.member.controller.dto.response.MemberDetailProfileResponse;
import com.repill.was.member.controller.dto.response.RecentlyViewedItemResponse;
import com.repill.was.member.controller.dto.response.view.MemberView;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountNotFoundException;
import com.repill.was.member.entity.account.AccountRepository;
import com.repill.was.member.entity.member.*;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.query.MemberLikeQueries;
import com.repill.was.member.query.MemberQueries;
import com.repill.was.member.query.vo.RecentlyViewedItemInfoVO;
import com.repill.was.member.service.AccountService;
import com.repill.was.member.service.MemberLikeService;
import com.repill.was.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import net.logstash.logback.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.repill.was.global.enums.Sort.CREATED_AT;
import static com.repill.was.global.enums.Sort.MEMBER_ID;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberQueries memberQueries;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final ItemValidateFactory itemValidateFactory;
    private final MemberLikeService memberLikeService;
    private final MemberLikeQueries memberLikeQueries;
    private final MemberService memberService;

    public void closeAccount(AccountId accountId, CloseAccountRequest request) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(BadRequestException::new);
        memberService.proceedClosingAccount(member, MemberDeleteCommand.request(request.getType(), request.getAdditionalInformation()));
    }

    @Transactional
    public void updateMyProfile(AccountId accountId, MemberUpdateProfileCommand memberUpdateProfileCommand) {
        Member memberTobeUpdated = memberRepository.findByAccountId(accountId).orElseThrow(BadRequestException::new);
        memberTobeUpdated.updateMyProfile(
                memberUpdateProfileCommand.getNickname(),
                memberUpdateProfileCommand.getProfileImageSrc()
        );
        memberRepository.save(memberTobeUpdated);
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
    public Page<RecentlyViewedItemResponse> getRecentlyViewItems(AccountId accountId, int size, int page) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        List<RecentlyViewedItem> recentlyViewedItems = member.getRecentlyViewedItems();
        List<RecentlyViewedItemResponse> list = recentlyViewedItems.parallelStream().map(one -> {
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
    public Page<RecentlyViewedItemResponse> getFavoriteItems(AccountId accountId, int size, int page) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        List<FavoriteItem> favoriteItems = member.getFavoriteItems();
        List<RecentlyViewedItemResponse> list = favoriteItems.parallelStream().map(one -> {
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
    public Page<MemberView> getFollowers(AccountId accountId, int size, int page) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        List<MemberFollower> memberFollowers = member.getMemberFollowers();
        List<MemberView> list = memberFollowers.parallelStream().map(one -> {
            Member followeredMember = memberQueries.findById(new MemberId(one.getFollowedId())).orElseThrow(MemberNotFoundException::new);
            return MemberView.newOne(followeredMember.getId().getId(), followeredMember.getNickname(), followeredMember.getImageSrc());
        }).collect(Collectors.toList());
        return PageUtils.makePage(list, MEMBER_ID.name(), size, page);
    }

    @Transactional
    public void addFollower(MemberId followeredId, AccountId accountId, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        member.addMemberFollowers(MemberFollower.newOne(
                followeredId.getId(),
                member.getId().getId()
        ));
        memberRepository.save(member);
    }

    @Transactional
    public void deleteFollower(MemberId followeredId, AccountId accountId, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        member.deleteMemberFollowers(MemberFollower.newOne(
                followeredId.getId(),
                member.getId().getId()
        ));
        memberRepository.save(member);
    }

    public void login(LoginCommand loginCommand) {
        Account account = accountRepository.findById(loginCommand.getAccountId()).orElseThrow(AccountNotFoundException::new);
        MemberId memberId = memberRepository.nextId();
        Member newMember = account.createMemberFromKakao(
                memberId,
                account.getId(),
                loginCommand.getProfileImage(),
                loginCommand.getNickname(),
                loginCommand.getId(),
                loginCommand.getAgeRange(),
                loginCommand.getBirthday(),
                loginCommand.getBirthdayType(),
                loginCommand.getGender(),
                loginCommand.getConnectedAt());
        memberRepository.save(newMember);
    }

    public void addInformation(MemberAddInformationCommand memberAddInformationCommand) {
        Member updateMember = memberQueries.findByAccountId(memberAddInformationCommand.getAccountId()).orElseThrow(MemberNotFoundException::new);
        updateMember.updateInformation(
                memberAddInformationCommand.getMyAddressInfo(),
                ImageListData.from(memberAddInformationCommand.getInterestingCategoryList()),
                memberAddInformationCommand.getInterestingAddress()
        );
        memberRepository.save(updateMember);
    }
}
