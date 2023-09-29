package com.repill.was.member.entity.member;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.ZonedDateTime;

@Embeddable
public class MemberFollower {

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long followerId;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long followedId;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private ZonedDateTime createdAt;

    public MemberFollower(Long followerId, Long followedId, ZonedDateTime createdAt) {
        this.followerId = followerId;
        this.followedId = followedId;
        this.createdAt = createdAt;
    }
}
