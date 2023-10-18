package com.repill.was.member.service;

import com.repill.was.global.exception.BadRequestException;
import com.repill.was.member.controller.command.MemberDeleteCommand;
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

    @Transactional
    public void proceedClosingAccount(Member member, MemberDeleteCommand memberDeleteCommand) {
        // todo 탈퇴 이유 넣는 컬럼 추가 (memberDeleteCommand 정보)
        Member deleteMember = memberRepository.findById(member.getId()).orElseThrow(BadRequestException::new);
        deleteMember.markAsClosed();
        memberRepository.save(deleteMember);
        Account account = accountRepository.findById(deleteMember.getAccountId()).orElseThrow(BadRequestException::new);
        accountRepository.delete(account);
        accountRepository.save(account);
    }

    @Transactional
    public Member update(AccountId accountId, String newImageSrc, String newNickname, boolean hideRealName) {
        Member memberTobeUpdated = memberRepository.findByAccountId(accountId).orElseThrow(BadRequestException::new);
        Account account = accountRepository.findById(accountId).orElseThrow(BadRequestException::new);
        memberRepository.save(memberTobeUpdated);
        return memberTobeUpdated;
    }
}
