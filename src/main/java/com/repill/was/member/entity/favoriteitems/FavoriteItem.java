package com.repill.was.member.entity.favoriteitems;

import com.repill.was.global.shard.enums.ItemType;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class FavoriteItem {

    @EmbeddedId
    private FavoriteItemId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId", nullable = false))
    MemberId memberId;

    @Column(columnDefinition = "VARCHAR(50)")
    private ItemType itemType;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long itemId;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private LocalDateTime createdAt;

    public FavoriteItem(FavoriteItemId id, MemberId memberId, ItemType itemType, Long itemId, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.itemType = itemType;
        this.itemId = itemId;
        this.createdAt = createdAt;
    }

    public static FavoriteItem newOne(FavoriteItemId id, MemberId memberId, ItemType itemType, Long itemId) {
        return new FavoriteItem(
                id,
                memberId,
                itemType,
                itemId,
                LocalDateTime.now()
        );
    }
}
