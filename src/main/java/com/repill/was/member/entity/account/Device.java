package com.repill.was.member.entity.account;

import com.repill.was.global.enums.OSType;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
public class Device {

    @Column(columnDefinition = "VARCHAR(256)", nullable = false)
    private String token;

    @Column(columnDefinition = "VARCHAR(256)", nullable = false)
    private String deviceId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private OSType osType;

    @Column(columnDefinition = "DATETIME(3)")
    private LocalDateTime logoutAt;

    public Device(String token, String deviceId, OSType osType, LocalDateTime logoutAt) {
        this.token = token;
        this.deviceId = deviceId;
        this.osType = osType;
        this.logoutAt = logoutAt;
    }

    public static Device newOne(String token, String deviceId, OSType osType) {
        return new Device(
                token,
                deviceId,
                osType,
                LocalDateTime.now()
        );
    }

    public Device logout() {
        return new Device(
                this.token,
                this.deviceId,
                this.osType,
                LocalDateTime.now()
        );
    }
}
