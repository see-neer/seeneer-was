package com.repill.was.member.entity.account;

import com.repill.was.global.enums.OSType;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.member.controller.dto.request.MemberLoginRequest;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberBannedException;
import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Entity
@NoArgsConstructor
public class Account implements Serializable {

    @EmbeddedId
    private AccountId id;

    @Embedded
    private Device device;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public Account(AccountId id, Device device, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.device = device;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Account newOne(AccountId id, OSType osType, String deviceId, String token) {
        Device device = Device.newOne(token, deviceId, osType);
        return new Account(
                id,
                device,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public Member createMemberFromKakao(
            MemberId memberId,
            AccountId accountId,
            String profileImage,
            String nickName,
            Long kakaoUserId,
            String ageRange,
            String birthday,
            String birthdayType,
            String gender,
            String connectedAt) {
        return new Member(
                memberId,
                accountId,
                profileImage,
                nickName,
                kakaoUserId,
                ageRange,
                birthday,
                birthdayType,
                gender,
                connectedAt
        );
    }

    public void changeDeviceInfo(OSType osType, String deviceId, String token) {
        this.device = Device.newOne(token, deviceId, osType);;
        this.updatedAt = LocalDateTime.now();
    }

    public void logout() {
        this.device = this.device.logout();
        this.updatedAt = LocalDateTime.now();
    }

    public void reLogin() {
        this.device = this.device.reLogin();
        this.updatedAt = LocalDateTime.now();
    }
}
