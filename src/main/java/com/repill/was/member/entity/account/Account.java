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
    @AttributeOverride(name = "id", column = @Column(name = "memberId", nullable = false))
    private MemberId memberId;

    @Column(columnDefinition = "VARCHAR(30)")
    @Enumerated(EnumType.STRING)
    private AuthType authType;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime authedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public Account(AccountId id, MemberId memberId, AuthType authType, LocalDateTime authedAt, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.authType = authType;
        this.authedAt = authedAt;
        this.createdAt = createdAt;
    }

    public Account authSocialLogin(Account account, MemberId memberId, AuthType authType) {
        return new Account(
                account.id,
                memberId,
                authType,
                LocalDateTime.now(),
                account.createdAt
        );
    }
}
