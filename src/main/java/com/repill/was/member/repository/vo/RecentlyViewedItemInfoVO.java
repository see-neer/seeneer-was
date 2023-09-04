package com.repill.was.member.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlyViewedItemInfoVO {
    private Long id;
    private Long itemId;
    private String itemType;

    @QueryProjection
    public RecentlyViewedItemInfoVO(Long id, Long itemId, RecentlyViewedItem.ItemType itemType) {
        this.id = id;
        this.itemId = itemId;
        this.itemType = itemType.name();
    }
}
