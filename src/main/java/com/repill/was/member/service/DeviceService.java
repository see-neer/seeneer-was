package com.repill.was.member.service;


import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.OSType;
import com.repill.was.member.entity.device.Device;
import com.repill.was.member.entity.device.DeviceRepository;
import com.repill.was.member.entity.member.MemberId;
import lombok.RequiredArgsConstructor;
import net.logstash.logback.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;


    @Transactional
    public void insertDeviceToken(AccountId accountId, OSType osType, String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        deviceRepository.clearTokenByToken(token);
        deviceRepository.save(Device.newOne(deviceRepository.nextId(), accountId, token, osType));
    }

    @Transactional
    public void removeDeviceToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        deviceRepository.clearTokenByToken(token);
    }

    public void removeAllByAccountId(AccountId accountId) {
        deviceRepository.clearTokenByAccountId(accountId);
    }
}

