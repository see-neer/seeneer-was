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
    @AttributeOverride(name = "id", column = @Column(name = "followerId", nullable = false))
    private Long followerId;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    @AttributeOverride(name = "id", column = @Column(name = "followedId", nullable = false))
    private Long followedId;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private LocalDateTime createdAt;

    public MemberFollower(Long followerId, Long followedId, LocalDateTime createdAt) {
        this.followerId = followerId;
        this.followedId = followedId;
        this.createdAt = createdAt;
    }

    public static MemberFollower newOne(Long followerId, Long followedId) {
        return new MemberFollower(followerId,
                followedId,
                LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberFollower that = (MemberFollower) o;
        return getFollowerId().equals(that.getFollowerId()) && getFollowedId().equals(that.getFollowedId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, followedId, createdAt);
    }
}
