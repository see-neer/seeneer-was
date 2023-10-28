package com.repill.was.member.entity.memberfollwer;

import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.AddLikeItemService;
import com.repill.was.member.entity.memberLike.MemberLikeId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@RequiredArgsConstructor
@Table(
        indexes = {
                @Index(name = "UK_MEMBER_ID_FOLLOWER_ID", columnList = "memberId, followerId", unique = true)
        }
)
public class MemberFollower {

    @EmbeddedId
    private MemberFollowerId memberFollowerId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId", nullable = false))
    private MemberId memberId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "followerId", nullable = false))
    private MemberId followerId;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public MemberFollower(MemberFollowerId memberFollowerId, MemberId memberId, MemberId followerId, LocalDateTime createdAt) {
        this.memberFollowerId = memberFollowerId;
        this.memberId = memberId;
        this.followerId = followerId;
        this.createdAt = createdAt;
    }

    public static MemberFollower newOne(MemberFollowerId memberFollowerId, MemberId memberId, MemberId followerId) {
        return new MemberFollower(memberFollowerId,
                memberId,
                followerId,
                LocalDateTime.now());
    }

    public boolean checkFollower(MemberId memberId) {
        if(this.memberId.equals(memberId)) return true;
        return false;
    }

    public boolean checkFollowered(MemberId memberId) {
        if(this.followerId.equals(memberId)) return true;
        return false;
    }
}
