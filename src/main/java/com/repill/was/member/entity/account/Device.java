package com.repill.was.member.entity.account;

import com.repill.was.global.enums.OSType;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
public class Device {

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "account_id"))
    )
    private AccountId accountId;

    @Column(columnDefinition = "VARCHAR(256)", nullable = false)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private OSType osType;

    @Column(columnDefinition = "DATETIME(3)")
    private LocalDateTime logoutAt;

    public Device(AccountId accountId, String token, OSType osType, LocalDateTime logoutAt) {
        this.accountId = accountId;
        this.token = token;
        this.osType = osType;
        this.logoutAt = logoutAt;
    }

    public Device logout() {
        return new Device(
                this.accountId,
                this.token,
                this.osType,
                LocalDateTime.now()
        );
    }
}
