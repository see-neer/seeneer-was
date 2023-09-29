package com.repill.was.member.entity.account;

import java.util.Optional;

public interface AccountRepository {

    AccountId nextId();

    Account save(Account account);

    Optional<Account> findById(AccountId accountId);

    Optional<Account> findByDevice(Device device);

    void delete(Account account);
}
