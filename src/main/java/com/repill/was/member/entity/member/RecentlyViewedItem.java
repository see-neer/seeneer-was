package com.repill.was.member.entity.member;

import com.repill.was.global.enums.ItemType;
import lombok.Getter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecentlyViewedItem that = (RecentlyViewedItem) o;
        return itemType == that.itemType && itemId.equals(that.itemId);
    }
}
