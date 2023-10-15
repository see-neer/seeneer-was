package com.repill.was.member.entity.account;

import java.util.Optional;

public interface AccountRepository {

    AccountId nextId();

    Account save(Account account);

    Optional<Account> findById(AccountId accountId);

    Optional<Account> findByDeviceId(String deviceId);

    void delete(Account account);
}
