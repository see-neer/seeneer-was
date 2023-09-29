package com.repill.was.member.repository.jpa;

import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.Device;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<Account, AccountId> {
    Optional<Account> findByDevice(Device device);
    Optional<Account> findById(AccountId accountId);
}
