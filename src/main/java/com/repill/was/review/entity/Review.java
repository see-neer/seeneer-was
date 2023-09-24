package com.repill.was.review.entity;

import com.repill.was.global.shard.enums.ItemType;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.BidiMap;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Review {

    @EmbeddedId
    ReviewId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId", nullable = false))
    private MemberId memberId;

    @Column(columnDefinition = "VARCHAR(50)")
    private ItemType itemType;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long itemId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public Review(ReviewId id, MemberId memberId, ItemType itemType, Long itemId, String content, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.itemType = itemType;
        this.itemId = itemId;
        this.content = content;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Review from(ReviewId id, MemberId memberId, ItemType itemType, Long itemId, String content) {
        return new Review(
                id,
                memberId,
                itemType,
                itemId,
                content,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
