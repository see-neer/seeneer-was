package com.repill.was.member.entity.member;

import com.repill.was.global.enums.ItemType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class FavoriteItem {

    @Column(columnDefinition = "VARCHAR(50)")
    private ItemType itemType;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long itemId;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private LocalDateTime createdAt;

    public FavoriteItem(ItemType itemType, Long itemId, LocalDateTime createdAt) {
        this.itemType = itemType;
        this.itemId = itemId;
        this.createdAt = createdAt;
    }

    public static FavoriteItem newOne(ItemType itemType, Long itemId) {
        return new FavoriteItem(
                itemType,
                itemId,
                LocalDateTime.now()
        );
    }
}
