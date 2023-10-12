package com.repill.was.member.entity.member;

import com.repill.was.global.enums.ItemType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDateTime;

@Embeddable
public class RecentlyViewedItem {

    @Column(columnDefinition = "VARCHAR(50)")
    private ItemType itemType;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long itemId;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private LocalDateTime createdAt;

    public RecentlyViewedItem(ItemType itemType, Long itemId, LocalDateTime createdAt) {
        this.itemType = itemType;
        this.itemId = itemId;
        this.createdAt = createdAt;
    }

    public static RecentlyViewedItem newOne(ItemType itemType, Long itemId) {
        return new RecentlyViewedItem(
                itemType,
                itemId,
                LocalDateTime.now()
        );
    }
}
