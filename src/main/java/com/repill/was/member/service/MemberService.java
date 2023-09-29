package com.repill.was.member.service;

import com.repill.was.global.exception.BadRequestException;
import com.repill.was.member.controller.dto.request.CloseAccountRequest;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountRepository;
import com.repill.was.global.enums.ClosingAccountReason;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberRepository;
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


//    public void logout(AccountId accountId, MemberId memberId, OSType osType, String token) {
//        deviceService.removeDeviceToken(token);
//    }

    @Transactional
    public void proceedClosingAccount(Member member, CloseAccountRequest request) {

        List<ClosingAccountReason> collect = request.getType().stream().map(one -> {
            return Optional.of(ClosingAccountReason.valueOf(one)).orElse(null);
        }).collect(Collectors.toList());

        Member member1 = memberRepository.findById(member.getId()).orElseThrow(BadRequestException::new);
//        member1.markAsClosed();
        memberRepository.save(member1);
        Account account = accountRepository.findById(member1.getAccountId()).orElseThrow(BadRequestException::new);
        accountRepository.delete(account);
//        deviceService.removeAllByAccountId(member1.getAccountId());
    }

    @Transactional
    public Member update(AccountId accountId, String newImageSrc, String newNickname, boolean hideRealName) {
        Member memberTobeUpdated = memberRepository.findByAccountId(accountId).orElseThrow(BadRequestException::new);
        Account account = accountRepository.findById(accountId).orElseThrow(BadRequestException::new);
        memberRepository.save(memberTobeUpdated);
        return memberTobeUpdated;
    }
}
