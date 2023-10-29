package com.repill.was.member.query;

import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountQueries {

    private final AccountRepository accountRepository;


    public Optional<Account> findById(AccountId accountId) {
        return accountRepository.findById(accountId);
    }

    public Optional<Account> findByDeviceId(String deviceId) {
        return accountRepository.findByDeviceId(deviceId);
    }
}
