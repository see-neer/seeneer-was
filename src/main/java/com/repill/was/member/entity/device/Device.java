package com.repill.was.member.entity.device;

import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.OSType;
import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(indexes = {
        @Index(name = "IDX_ACCOUNT_ID", columnList = "accountId"),
        @Index(name = "IDX_MEMBER_ID", columnList = "memberId"),
        @Index(name = "IDX_TOKEN", columnList = "token")
})
@NoArgsConstructor
public class Device {
    @EmbeddedId
    DeviceId deviceId;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "accountId", nullable = false))
    private AccountId accountId;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId"))
    private MemberId memberId;
    @Column(columnDefinition = "VARCHAR(256)", nullable = false)
    private String token;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private OSType osType;

    public static Device newOne(DeviceId deviceId, AccountId accountId, MemberId memberId, String token, OSType osType) {
        Device d = new Device();
        d.deviceId = deviceId;
        d.accountId = accountId;
        d.memberId = memberId;
        d.token = token;
        d.osType = osType;
        return d;
    }
    public static Device newOne(DeviceId deviceId, AccountId accountId, String token, OSType osType) {
        return Device.newOne(deviceId, accountId, null, token, osType);
    }

    public void createMember(MemberId memberId) {
        this.memberId = memberId;
    }
}

