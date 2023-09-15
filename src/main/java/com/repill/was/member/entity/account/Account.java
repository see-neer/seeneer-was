package com.repill.was.member.entity.account;

import com.repill.was.global.shard.enums.AuthType;
import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(
        indexes = {
                @Index(name = "UK_ID_MEMBER_ID", columnList = "id, memberId", unique = true)
        }
)
@NoArgsConstructor
public class Account implements Serializable {

    @EmbeddedId
    private AccountId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId"))
    private MemberId memberId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private OSType osType;

    @Column(columnDefinition = "VARCHAR(50)")
    private String deviceId;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public Account(AccountId id, MemberId memberId, OSType osType, String deviceId, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.osType = osType;
        this.deviceId = deviceId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Account newOne(AccountId accountId, OSType osType, String deviceId) {
        return new Account(
                accountId,
                null,
                osType,
                deviceId,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public void createMember(MemberId memberId) {
        this.memberId = memberId;
    }
}
