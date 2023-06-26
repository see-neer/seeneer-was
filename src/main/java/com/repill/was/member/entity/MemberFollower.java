package com.repill.was.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "UK_FOLLOWER_ID_AND_FOLLOWED_ID", columnList = "followerId, followedId", unique = true)
})
public class MemberFollower {

    @EmbeddedId
    MemberFollowerId memberFollowerId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "followerId", nullable = false))
    MemberId followerId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "followedId", nullable = false))
    MemberId followedId;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private ZonedDateTime createdAt;
}
