package com.repill.was.member.entity.memerblock;

import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private ZonedDateTime createdAt;
}
