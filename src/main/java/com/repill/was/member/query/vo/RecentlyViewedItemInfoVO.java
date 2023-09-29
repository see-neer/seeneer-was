package com.repill.was.member.query.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.repill.was.global.enums.ItemType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlyViewedItemInfoVO {
    private Long id;
    private Long itemId;
    private String itemType;

    @QueryProjection
    public RecentlyViewedItemInfoVO(Long id, Long itemId, ItemType itemType) {
        this.id = id;
        this.itemId = itemId;
        this.itemType = itemType.name();
    }
}
