package com.repill.was.member.controller.dto.response;


import com.repill.was.global.enums.ItemType;
import com.repill.was.global.factory.itemvalidate.ItemValidateFactory;
import com.repill.was.global.factory.itemvalidate.ItemValidator;
import com.repill.was.global.utils.TimeUtils;

import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.Market;
import com.repill.was.item.query.vo.ItemVO;
import com.repill.was.member.entity.member.FavoriteItem;
import com.repill.was.member.entity.member.RecentlyViewedItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RecentlyViewedItemResponse {
    private Long itemId;
    private String itemType;
    private String name;
    private String date;
    private List<String> images;
    private Integer score;
    private Integer reviewCount;

    private String createdAt;
    private String updatedAt;

    public RecentlyViewedItemResponse(Long itemId, String itemType, String name, String date, List<String> images, Integer score, Integer reviewCount, String createdAt, String updatedAt) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.name = name;
        this.date = date;
        this.images = images;
        this.score = score;
        this.reviewCount = reviewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static RecentlyViewedItemResponse from(FavoriteItem favoriteItem, ItemValidateFactory itemValidateFactory) {
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(favoriteItem.getItemType());
        ItemVO itemInfo = validatorBy.getItemInfo(favoriteItem.getItemId());
        return RecentlyViewedItemResponse.fromItemVO(itemInfo);
    }

    public static RecentlyViewedItemResponse from(RecentlyViewedItem recentlyViewedItem, ItemValidateFactory itemValidateFactory) {
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(recentlyViewedItem.getItemType());
        ItemVO itemInfo = validatorBy.getItemInfo(recentlyViewedItem.getItemId());
        return RecentlyViewedItemResponse.fromItemVO(itemInfo);
    }

    private static RecentlyViewedItemResponse fromItemVO(ItemVO itemVO) {
        return new RecentlyViewedItemResponse(
                itemVO.getId(),
                ItemType.MARKET.name(),
                itemVO.getName(),
                TimeUtils.convertToISO_8061(itemVO.getCreatedAt()),
                itemVO.getImageSrc(),
                5,
                100,
                TimeUtils.convertToISO_8061(itemVO.getCreatedAt()),
                TimeUtils.convertToISO_8061(itemVO.getUpdateAt())
        );
    }
}
