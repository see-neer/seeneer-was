package com.repill.was.member.entity.device;

import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.MemberId;

import java.util.List;

public interface DeviceRepository {
    DeviceId nextId();
    Device save(Device d);
    List<Device> findAllByAccountId(AccountId accountId);
    List<Device> findAllByMemberId(MemberId memberId);
    void clearTokenByToken(String token);
    void clearTokenByAccountId(AccountId accountId);
}
