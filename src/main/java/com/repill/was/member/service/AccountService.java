package com.repill.was.member.service;

import com.repill.was.member.controller.dto.request.MemberLogoutRequest;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountNotFoundException;
import com.repill.was.member.entity.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void logout(AccountId accountId, MemberLogoutRequest memberLogoutRequest) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        account.logout();
        accountRepository.save(account);
    }
}
