package com.repill.was.member.entity.account;

import com.repill.was.global.enums.OSType;
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
    private Device device;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId"))
    private MemberId memberId;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public Account(AccountId id, Device device, MemberId memberId, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.device = device;
        this.memberId = memberId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Account newOne(AccountId accountId, Device device) {
        return new Account(
                accountId,
                device,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public void createMember(MemberId memberId) {
        this.memberId = memberId;
    }
}
