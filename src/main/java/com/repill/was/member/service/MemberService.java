package com.repill.was.member.service;

import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.model.EntityListData;
import com.repill.was.member.controller.command.LoginCommand;
import com.repill.was.member.controller.command.MemberAddInformationCommand;
import com.repill.was.member.controller.command.MemberDeleteCommand;
import com.repill.was.member.controller.command.MemberUpdateProfileCommand;
import com.repill.was.member.controller.dto.request.CloseAccountRequest;
import com.repill.was.member.controller.dto.request.MemberAlarmSettingRequest;
import com.repill.was.member.controller.dto.response.view.MemberView;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountNotFoundException;
import com.repill.was.member.entity.account.AccountRepository;
import com.repill.was.global.enums.ClosingAccountReason;
import com.repill.was.member.entity.member.*;
import com.repill.was.member.entity.memberCloseHistory.MemberCloseHistory;
import com.repill.was.member.entity.memberCloseHistory.MemberCloseHistoryRepository;
import com.repill.was.member.entity.memberfollwer.MemberFollower;
import com.repill.was.member.entity.memberfollwer.MemberFollowerFoundException;
import com.repill.was.member.entity.memberfollwer.MemberFollowerRepository;
import com.repill.was.member.query.AccountQueries;
import com.repill.was.member.query.MemberQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final MemberQueries memberQueries;
    private final MemberFollowerRepository memberFollowerRepository;
    private final AccountQueries accountQueries;
    private final MemberCloseHistoryRepository memberCloseHistoryRepository;

    @Transactional
    public MemberView login(LoginCommand loginCommand) {
        Account loginAccount = accountQueries.findById(loginCommand.getAccountId()).orElseThrow(AccountNotFoundException::new);
        Optional<Member> byAccountId = memberQueries.findByAccountId(loginAccount.getId());
        Optional<Member> byKaKaoId = memberQueries.findByKaKaoId(loginCommand.getId());
        if(byKaKaoId.isPresent()) {
            byKaKaoId.get().markReCreatedMember(loginCommand.getAccountId());
            memberRepository.save(byKaKaoId.get());
            return MemberView.newOne(byKaKaoId.get().getId().getId(), byKaKaoId.get().getNickname(), byKaKaoId.get().getImageSrc());
        }
        if(byAccountId.isEmpty()) {
            MemberId memberId = memberRepository.nextId();
            Member newMember = loginAccount.createMemberFromKakao(
                    memberId,
                    loginAccount.getId(),
                    loginCommand.getProfileImage(),
                    loginCommand.getNickname(),
                    loginCommand.getId(),
                    loginCommand.getAgeRange(),
                    loginCommand.getBirthday(),
                    loginCommand.getBirthdayType(),
                    loginCommand.getGender(),
                    loginCommand.getConnectedAt());
            memberRepository.save(newMember);
            return MemberView.newOne(memberId.getId(), newMember.getNickname(), newMember.getImageSrc());
        }
        loginAccount.reLogin();
        accountRepository.save(loginAccount);
        return MemberView.newOne(byAccountId.get().getId().getId(), byAccountId.get().getNickname(), byAccountId.get().getImageSrc());
    }

    @Transactional
    public void addFollower(MemberId followeredId, AccountId accountId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        MemberFollower newMemberFollower = MemberFollower.newOne(
                memberFollowerRepository.nextId(),
                member.getId(),
                followeredId
        );
        memberFollowerRepository.save(newMemberFollower);
    }

    @Transactional
    public void deleteFollower(MemberId followeredId, AccountId accountId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        MemberFollower deleteMemberFollower = memberFollowerRepository.findFolloweredByMemberIdAndFollowerId(member.getId(), followeredId).orElseThrow(MemberFollowerFoundException::new);
        memberFollowerRepository.delete(deleteMemberFollower);
    }

    @Transactional
    public void addInformation(MemberAddInformationCommand memberAddInformationCommand) {
        Member updateMember = memberQueries.findByAccountId(memberAddInformationCommand.getAccountId()).orElseThrow(MemberNotFoundException::new);
        updateMember.updateInformation(
                memberAddInformationCommand.getMyAddressInfo(),
                EntityListData.from(memberAddInformationCommand.getInterestingCategoryList()),
                memberAddInformationCommand.getInterestingAddress(),
                memberAddInformationCommand.getNickname()
        );
        memberRepository.save(updateMember);
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

    @Transactional
    public void proceedClosingAccount(Member member, MemberDeleteCommand memberDeleteCommand) {
        Member deleteMember = memberRepository.findById(member.getId()).orElseThrow(BadRequestException::new);
        deleteMember.markAsClosed();
        memberRepository.save(deleteMember);
        Account account = accountRepository.findById(deleteMember.getAccountId()).orElseThrow(BadRequestException::new);
        accountRepository.delete(account);
        memberCloseHistoryRepository.save(MemberCloseHistory.newOne(member.getId(), EntityListData.from(memberDeleteCommand.getType()), memberDeleteCommand.getAdditionalInformation()));
    }

    @Transactional
    public void updateAlarmSetting(AccountId accountId, MemberAlarmSettingRequest memberAlarmSettingRequest) {
        Member updateMember = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        updateMember.updateMemberSetting(
                MemberSetting.newOne(
                        memberAlarmSettingRequest.isMarketingTermsAgreed(),
                        memberAlarmSettingRequest.isAppUpdateNotificationEnabled(),
                        memberAlarmSettingRequest.isReviewCommentNotificationEnabled(),
                        memberAlarmSettingRequest.isServiceNoticeNotificationEnabled()
                )
        );
        memberRepository.save(updateMember);
    }
}
