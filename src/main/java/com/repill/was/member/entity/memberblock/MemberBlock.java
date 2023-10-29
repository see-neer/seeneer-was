package com.repill.was.member.entity.memberblock;

import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Entity
@Table(indexes = {
        @Index(name = "IDX_MEMBER_ID_TARGET_MEMBER_ID", columnList = "memberId, targetMemberId"),
})
@NoArgsConstructor
public class MemberBlock {
    @EmbeddedId
    private MemberBlockId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId", nullable = false))
    private MemberId memberId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "targetMemberId", nullable = false))
    private MemberId targetMemberId;

    @Column(columnDefinition = "DATETIME(3)", nullable = false)
    private LocalDateTime createdAt;

    public MemberBlock(MemberBlockId id, MemberId memberId, MemberId targetMemberId, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.targetMemberId = targetMemberId;
        this.createdAt = createdAt;
    }

    public static MemberBlock newOne(MemberBlockId id, MemberId memberId, MemberId targetMemberId) {
        return new MemberBlock(id,
                memberId,
                targetMemberId,
                LocalDateTime.now());
    }
}
