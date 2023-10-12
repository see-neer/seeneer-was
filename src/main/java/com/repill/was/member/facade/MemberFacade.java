package com.repill.was.member.facade;




import com.repill.was.global.enums.ItemType;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.factory.itemvalidate.ItemValidateFactory;
import com.repill.was.global.factory.itemvalidate.ItemValidator;
import com.repill.was.global.model.ImageListData;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteFavoriteItem(MemberId memberId) {
//        FavoriteItem favoriteItem = favoriteItemRepository.findByIdAndAccountId(id, memberId).orElseThrow(() -> new BadRequestException("존재하지 않는 최근 본 목록 값 입니다."));
//        favoriteItemRepository.delete(favoriteItem);
    }

    public List<RecentlyViewedItemResponse> getFavoriteItems(AccountId accountId, int size, Long cursorId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);

        return null;
    }

    public void deleteRecentlyView(Member member) {
//        RecentlyViewedItem recentlyViewedItem = recentlyViewedItemRepository.findByIdAndAccountId(id, member.getId()).orElseThrow(() -> new BadRequestException("존재하지 않는 최근 본 목록 값 입니다."));
//        recentlyViewedItem.canDeleteItem(validateIsActiveItemService, member);
//        recentlyViewedItemRepository.delete(recentlyViewedItem);
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
