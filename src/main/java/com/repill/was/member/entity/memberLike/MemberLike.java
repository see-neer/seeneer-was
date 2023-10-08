package com.repill.was.member.entity.memberLike;

import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@RequiredArgsConstructor
@Table(
        indexes = {
                @Index(name = "UK_MEMBER_ID_ITEM_ID_ITEM_TYPE", columnList = "memberId, itemId, likeType", unique = true)
        }
)
public class MemberLike {

    public enum LikeType {
        FESTIVAL, MARKET, COMMENT
    }

    @EmbeddedId
    private MemberLikeId memberLikeId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId", nullable = false))
    private MemberId memberId;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long itemId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(50)")
    private LikeType likeType;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public MemberLike(MemberLikeId memberLikeId, MemberId memberId, Long itemId, LikeType likeType, LocalDateTime createdAt) {
        this.memberLikeId = memberLikeId;
        this.memberId = memberId;
        this.itemId = itemId;
        this.likeType = likeType;
        this.createdAt = createdAt;
    }

    public static MemberLike newOne(MemberLikeId memberLikeId, MemberId memberId, Long itemId, LikeType likeType){
        return new MemberLike(
                memberLikeId,
                memberId,
                itemId,
                likeType,
                LocalDateTime.now()
        );
    }

    public static void addLike(AddLikeItemService addLikeItemService, LikeType likeType, Long itemId, MemberLike newMemberLike) {
        addLikeItemService.addLike(itemId, likeType, newMemberLike);
    }
}
