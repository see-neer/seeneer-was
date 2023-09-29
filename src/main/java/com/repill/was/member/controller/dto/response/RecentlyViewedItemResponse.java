package com.repill.was.member.controller.dto.response;


import com.repill.was.global.enums.ItemType;
import com.repill.was.global.utils.TimeUtils;

import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.Market;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecentlyViewedItemResponse {

    private Long id;
    private Long itemId;
    private String itemType;
    private String name;
    private String date;
    private List<String> images;
    private Integer score;
    private Integer reviewCount;

    public RecentlyViewedItemResponse(Long id, Long itemId, String itemType, String name, String date, List<String> images, Integer score, Integer reviewCount) {
        this.id = id;
        this.itemId = itemId;
        this.itemType = itemType;
        this.name = name;
        this.date = date;
        this.images = images;
        this.score = score;
        this.reviewCount = reviewCount;
    }

    public static RecentlyViewedItemResponse fromMarket(Market market, Long id) {
        return new RecentlyViewedItemResponse(
                id,
                market.getId().getId(),
                ItemType.MARKET.name(),
                market.getName(),
                market.getDate(),
                market.getImages().getImages(),
                5,
                100
        );
    }

    public static RecentlyViewedItemResponse fromFestival(Festival festival, Long id) {
        return new RecentlyViewedItemResponse(
                id,
                festival.getId().getId(),
                ItemType.FESTIVAL.name(),
                festival.getName(),
                TimeUtils.convertToISO_8061(festival.getDate()),
                festival.getImages().getImages(),
                5,
                100
        );
    }
}
