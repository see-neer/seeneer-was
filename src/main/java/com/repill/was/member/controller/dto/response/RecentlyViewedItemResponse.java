package com.repill.was.member.controller.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.repill.was.festival.entity.Festival;
import com.repill.was.festival.entity.FestivalId;
import com.repill.was.market.entity.Market;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlyViewedItemResponse {

    private Long id;
    private Long itemId;
    private String itemType;
    private String name;
    private String date;
    private String imageSrc;
    private Integer score;
    private Integer reviewCount;

    public RecentlyViewedItemResponse(Long id, Long itemId, String itemType, String name, String date, String imageSrc, Integer score, Integer reviewCount) {
        this.id = id;
        this.itemId = itemId;
        this.itemType = itemType;
        this.name = name;
        this.date = date;
        this.imageSrc = imageSrc;
        this.score = score;
        this.reviewCount = reviewCount;
    }

    public static RecentlyViewedItemResponse fromMarket(Market market, Long id) {
        return new RecentlyViewedItemResponse(
                id,
                market.getId().getId(),
                RecentlyViewedItem.ItemType.MARKET.name(),
                market.getName(),
                market.getDate(),
                market.getImageSrc(),
                5,
                100
        );
    }

    public static RecentlyViewedItemResponse fromFestival(Festival festival, Long id) {
        return new RecentlyViewedItemResponse(
                id,
                festival.getId().getId(),
                RecentlyViewedItem.ItemType.FESTIVAL.name(),
                festival.getName(),
                festival.getDate(),
                festival.getImageSrc(),
                5,
                100
        );
    }
}
