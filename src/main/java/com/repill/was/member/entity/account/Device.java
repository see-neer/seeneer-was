package com.repill.was.member.entity.account;

import com.repill.was.global.enums.OSType;

import javax.persistence.*;

@Embeddable
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
}
