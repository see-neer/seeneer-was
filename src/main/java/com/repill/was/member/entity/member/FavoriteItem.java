package com.repill.was.member.entity.member;

import com.repill.was.global.enums.ItemType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor
public class FavoriteItem {

    @Enumerated(EnumType.STRING)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteItem that = (FavoriteItem) o;
        return itemType == that.itemType && itemId.equals(that.itemId);
    }
}
