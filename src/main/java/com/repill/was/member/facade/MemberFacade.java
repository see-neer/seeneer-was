package com.repill.was.member.facade;

import com.repill.was.global.enums.ItemType;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.factory.itemvalidate.ItemValidateFactory;
import com.repill.was.global.factory.itemvalidate.ItemValidator;
import com.repill.was.global.model.ImageListData;
import com.repill.was.global.utils.PageUtils;
import com.repill.was.item.query.vo.ItemVO;
import com.repill.was.member.controller.command.LoginCommand;
import com.repill.was.member.controller.command.MemberAddInformationCommand;
import com.repill.was.member.controller.command.MemberNickNameDuplicatedCheckCommand;
import com.repill.was.member.controller.dto.response.RecentlyViewedItemResponse;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountNotFoundException;
import com.repill.was.member.entity.account.AccountRepository;
import com.repill.was.member.entity.member.*;
import com.repill.was.member.query.MemberQueries;
import com.repill.was.member.query.vo.RecentlyViewedItemInfoVO;
import io.lettuce.core.GeoArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.repill.was.global.enums.Sort.CREATED_AT;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberQueries memberQueries;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final ItemValidateFactory itemValidateFactory;

    public Boolean checkDuplicateNickname(MemberNickNameDuplicatedCheckCommand memberNickNameDuplicatedCheckCommand) {
        if(memberNickNameDuplicatedCheckCommand.isUseKakaoNickname()) {
            return true;
        }
        return memberQueries.findByMemberNickName(memberNickNameDuplicatedCheckCommand.getCheckNickName()).isEmpty();
    }

    public void addRecentlyView(ItemType itemType, AccountId accountId, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        validatorBy.addRecentlyViewedItem(member, itemId);
    }

    public void addFavoriteItem(ItemType itemType, AccountId accountId, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        validatorBy.addFavoriteItem(member, itemId);
    }

    public void deleteFavoriteItem(ItemType itemType, AccountId accountId, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        validatorBy.deleteFavoriteItem(member, itemId);
    }

    //todo 리펙토링 필요
    @Transactional(readOnly = true)
    public Page<RecentlyViewedItemResponse> getRecentlyViewItems(AccountId accountId, int size, int page) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        List<RecentlyViewedItem> recentlyViewedItems = member.getRecentlyViewedItems();
        List<RecentlyViewedItemResponse> list = recentlyViewedItems.parallelStream().map(one -> {
            return RecentlyViewedItemResponse.from(one, itemValidateFactory);
        }).collect(Collectors.toList());
        return PageUtils.makePage(list, CREATED_AT.name(), size, page);
    }

    //todo 리펙토링 필요
    @Transactional(readOnly = true)
    public Page<RecentlyViewedItemResponse> getFavoriteItems(AccountId accountId, int size, int page) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        List<FavoriteItem> favoriteItems = member.getFavoriteItems();
        List<RecentlyViewedItemResponse> list = favoriteItems.parallelStream().map(one -> {
            return RecentlyViewedItemResponse.from(one, itemValidateFactory);
        }).collect(Collectors.toList());
        return PageUtils.makePage(list, CREATED_AT.name(), size, page);
    }

    public void deleteRecentlyView(ItemType itemType, AccountId accountId, Long itemId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        validatorBy.deleteRecentlyViewedItem(member, itemId);
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
