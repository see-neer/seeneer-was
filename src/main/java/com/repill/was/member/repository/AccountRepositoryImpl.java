package com.repill.was.member.repository;

import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountRepository;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberRepository;
import com.repill.was.member.repository.jpa.AccountJpaRepository;
import com.repill.was.member.repository.jpa.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final SequenceGenerator sequenceGenerator;
    private final AccountJpaRepository accountJpaRepository;

    @Override
    public AccountId nextId() {
        return new AccountId(sequenceGenerator.generate());
    }

    @Override
    public Account save(Account account) {
        return accountJpaRepository.save(account);
    }

    @Override
    public Optional<Account> findById(AccountId accountId) {
        return accountJpaRepository.findById(accountId);
    }

    @Override
    public Optional<Account> findByDeviceId(String deviceId) {
        return accountJpaRepository.findByDeviceId(deviceId);
    }

    @Override
    public void delete(Account account) {
        accountJpaRepository.delete(account);
    }
}
