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

    @Column(columnDefinition = "TEXT")
    String imageSrc;

    @Column(columnDefinition = "VARCHAR(50)")
    private String nickname;

    @Column(columnDefinition = "DATETIME(3)")
    private LocalDateTime closedAt; // 탈퇴 사용자인지 flag

    @Column(columnDefinition = "DATETIME(3)")
    private LocalDateTime lastNickNameChangedAt;


    @Column(nullable = false, unique = true)
    private Long kakaoUserId; // Kakao 사용자의 고유 ID (또는 다른 적절한 유일한 식별자)

    private String ageRange;
    private String birthday;
    private String birthdayType;
    private String gender;

    @Column(nullable = false)
    private String connectedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;
    
    public Member(MemberId memberId, String imageSrc, String nickname) {
        this.id = memberId;
        this.imageSrc = imageSrc;
        this.nickname = nickname;
    }

    public Member(MemberId id, AccountId accountId, String address, String imageSrc, String nickname, LocalDateTime closedAt, LocalDateTime lastNickNameChangedAt, Long kakaoUserId, String ageRange, String birthday, String birthdayType, String gender, String connectedAt) {
        this.id = id;
        this.accountId = accountId;
        this.address = address;
        this.imageSrc = imageSrc;
        this.nickname = nickname;
        this.closedAt = closedAt;
        this.lastNickNameChangedAt = lastNickNameChangedAt;
        this.kakaoUserId = kakaoUserId;
        this.ageRange = ageRange;
        this.birthday = birthday;
        this.birthdayType = birthdayType;
        this.gender = gender;
        this.connectedAt = connectedAt;
    }

    public static Member notExistMember() {
        return new Member(null, null, null);
    }

    public void markAsClosed() {
        this.closedAt = LocalDateTime.now();
    }

    public void tryChangeNickname(String nickname) {
        this.nickname = nickname;
        this.lastNickNameChangedAt = LocalDateTime.now();
    }

}
