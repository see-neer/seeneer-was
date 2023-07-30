package com.repill.was.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainResponse {

    String message;
    List<String> categoryList;
    List<BannerInfo> bannerInfo;

    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    public static class BannerInfo {
        String bannerType;
        String imageSrc;
        String name;
        String StartDate;
        String endDate;


        public static BannerInfo newMarketBanner(String imageSrc, String name) {
            return new BannerInfo(
                    BannerType.MARKET.name(),
                    imageSrc,
                    name,
                    null,
                    null
            );
        }

        public static BannerInfo newFestivalBanner(String imageSrc, String name, String startDate, String endDate) {
            return new BannerInfo(
                    BannerType.FESTIVAL.name(),
                    imageSrc,
                    name,
                    startDate,
                    endDate
            );
        }
    }

    public enum BannerType {
        MARKET, FESTIVAL
    }

    public static MainResponse from(String message, List<String> categoryList, List<BannerInfo> bannerInfo) {
        return new MainResponse(message, categoryList, bannerInfo);
    }
}
