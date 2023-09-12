package com.repill.was.member.repository;


import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.device.Device;
import com.repill.was.member.entity.device.DeviceId;
import com.repill.was.member.entity.device.DeviceRepository;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.repository.jpa.DeviceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeviceRepositoryImpl implements DeviceRepository {
    private final SequenceGenerator sequenceGenerator;
    private final DeviceJpaRepository deviceJpaRepository;

    @Override
    public DeviceId nextId() {
        return new DeviceId(sequenceGenerator.generate());
    }

    @Override
    public Device save(Device d) {
        return deviceJpaRepository.save(d);
    }

    @Override
    public List<Device> findAllByAccountId(AccountId accountId) {
        return deviceJpaRepository.findAllByAccountId(accountId);
    }

    @Override
    public List<Device> findAllByMemberId(MemberId memberId) {
        return deviceJpaRepository.findAllByMemberId(memberId);
    }

    @Override
    public void clearTokenByToken(String token) {
        deviceJpaRepository.deleteAllByToken(token);
    }

    @Override
    public void clearTokenByAccountId(AccountId accountId) {
        deviceJpaRepository.deleteAllByAccountId(accountId);
    }
}