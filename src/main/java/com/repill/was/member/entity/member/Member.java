package com.repill.was.member.entity.member;

import com.repill.was.member.entity.account.AccountId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Locale;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @EmbeddedId
    MemberId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "accountId", nullable = false))
    AccountId accountId;

    String address;

    String imageSrc;

    @Column(columnDefinition = "VARCHAR(50)")
    private String nickname;

    @Column(columnDefinition = "DATETIME(3)")
    private LocalDateTime closedAt; // 탈퇴 사용자인지 flag

    @Column(columnDefinition = "DATETIME(3)")
    private LocalDateTime lastNickNameChangedAt;
    
    public Member(String imageSrc, String nickname) {
        this.imageSrc = imageSrc;
        this.nickname = nickname;
    }

    public  Member(MemberId id, AccountId accountId, String address, String nickname) {
        this.id = id;
        this.accountId = accountId;
        this.address = address;
        this.nickname = nickname;
    }

    public static Member notExistMember() {
        return new Member(null, null);
    }

    public void markAsClosed() {
        this.closedAt = LocalDateTime.now();
    }

    public void tryChangeNickname(String nickname) {
        this.nickname = nickname;
        this.lastNickNameChangedAt = LocalDateTime.now();
    }

}
