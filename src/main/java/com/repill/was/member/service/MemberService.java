package com.repill.was.member.service;

import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.model.EntityListData;
import com.repill.was.member.controller.command.LoginCommand;
import com.repill.was.member.controller.command.MemberAddInformationCommand;
import com.repill.was.member.controller.command.MemberDeleteCommand;
import com.repill.was.member.controller.command.MemberUpdateProfileCommand;
import com.repill.was.member.controller.dto.request.CloseAccountRequest;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountNotFoundException;
import com.repill.was.member.entity.account.AccountRepository;
import com.repill.was.global.enums.ClosingAccountReason;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberNotFoundException;
import com.repill.was.member.entity.member.MemberRepository;
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
    public boolean login(LoginCommand loginCommand) {
        Account loginAccount = accountQueries.findById(loginCommand.getAccountId()).orElseThrow(AccountNotFoundException::new);
        if(memberQueries.findByAccountId(loginAccount.getId()).isEmpty()) {
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
            return true;
        }
        loginAccount.reLogin();
        return false;
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
        accountRepository.save(account);
        memberCloseHistoryRepository.save(MemberCloseHistory.newOne(member.getId(), memberDeleteCommand.getType(), memberDeleteCommand.getAdditionalInformation()));
    }

    @Transactional
    public Member update(AccountId accountId, String newImageSrc, String newNickname, boolean hideRealName) {
        Member memberTobeUpdated = memberRepository.findByAccountId(accountId).orElseThrow(BadRequestException::new);
        Account account = accountRepository.findById(accountId).orElseThrow(BadRequestException::new);
        memberRepository.save(memberTobeUpdated);
        return memberTobeUpdated;
    }
}
