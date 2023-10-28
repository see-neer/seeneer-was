package com.repill.was.member.entity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class MemberFollower {

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long followerId;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private LocalDateTime createdAt;

    public MemberFollower(Long followerId, LocalDateTime createdAt) {
        this.followerId = followerId;
        this.createdAt = createdAt;
    }

    public static MemberFollower newOne(Long followerId) {
        return new MemberFollower(followerId,
                LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberFollower that = (MemberFollower) o;
        return Objects.equals(getFollowerId(), that.getFollowerId()) && Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFollowerId(), getCreatedAt());
    }
}
