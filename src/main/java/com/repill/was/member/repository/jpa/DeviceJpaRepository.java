package com.repill.was.member.repository.jpa;

import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.device.Device;
import com.repill.was.member.entity.device.DeviceId;
import com.repill.was.member.entity.member.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface DeviceJpaRepository extends JpaRepository<Device, DeviceId> {
    List<Device> findAllByAccountId(AccountId accountId);
    List<Device> findAllByMemberId(MemberId memberId);
    @Modifying
    @Transactional
    @Query("delete from Device d where d.token = :token")
    void deleteAllByToken(@Param("token") String token);
    @Modifying
    @Transactional
    @Query("delete from Device d where d.accountId = :accountId")
    void deleteAllByAccountId(@Param("accountId") AccountId accountId);
}
