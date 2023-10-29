package com.repill.was.review.entity;

import com.repill.was.global.enums.ItemType;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.model.EntityListData;
import com.repill.was.global.model.EntityListDataConverter;
import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private MemberId reviewerId;

    @Column(columnDefinition = "VARCHAR(50)")
    private ItemType itemType;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long itemId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Convert(converter = EntityListDataConverter.class)
    @Column(columnDefinition = "TEXT")
    private EntityListData images;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public Review(ReviewId id, MemberId reviewerId, ItemType itemType, Long itemId, String content, EntityListData images, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.reviewerId = reviewerId;
        this.itemType = itemType;
        this.itemId = itemId;
        this.content = content;
        this.images = images;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Review from(ReviewId id, MemberId memberId, ItemType itemType, Long itemId, String content, EntityListData images) {
        return new Review(
                id,
                memberId,
                itemType,
                itemId,
                content,
                images,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public void updateImage(EntityListData changeImageList, String changeContent) {
        this.images = changeImageList;
        this.content = changeContent;
        this.updatedAt = LocalDateTime.now();
    }
}
