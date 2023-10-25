package com.repill.was.global.enums;

import com.repill.was.operation.entity.CategoryNotFoundException;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public enum FavoriteCategory {
    DAILY_LIFE(Map.of(Locale.ENGLISH, "DAILY_LIFE",Locale.KOREA, "일상생활"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "https://repill-dev.s3.ap-northeast-2.amazonaws.com/undraw_A_day_at_the_park_re_9kxj.png")),
    EXERCISE(Map.of(Locale.ENGLISH, "EXERCISE",Locale.KOREA, "운동"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "https://repill-dev.s3.ap-northeast-2.amazonaws.com/undraw_playing_golf_sdt3.png")),
    HEALTH(Map.of(Locale.ENGLISH, "HEALTH",Locale.KOREA, "건강"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "https://repill-dev.s3.ap-northeast-2.amazonaws.com/undraw_medicine_b1ol.png")),
    FINANCIAL_TECHNOLOGY(Map.of(Locale.ENGLISH, "FINANCIAL_TECHNOLOGY",Locale.KOREA, "재태크"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "https://repill-dev.s3.ap-northeast-2.amazonaws.com/undraw_Savings_re_eq4w.png")),
    HOBBY(Map.of(Locale.ENGLISH, "HOBBY",Locale.KOREA, "취미(문화생활)"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "https://repill-dev.s3.ap-northeast-2.amazonaws.com/undraw_Cooking_p7m1.png")),
    TRAVEL(Map.of(Locale.ENGLISH, "TRAVEL",Locale.KOREA, "여행"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "https://repill-dev.s3.ap-northeast-2.amazonaws.com/undraw_Aircraft_re_m05i.png")),
    RESTAURANT_CAFE(Map.of(Locale.ENGLISH, "RESTAURANT_CAFE",Locale.KOREA, "레스토랑/카페"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "https://repill-dev.s3.ap-northeast-2.amazonaws.com/undraw_breakfast_psiw.png")),
    SHOPPING(Map.of(Locale.ENGLISH, "SHOPPING",Locale.KOREA, "쇼핑"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "https://repill-dev.s3.ap-northeast-2.amazonaws.com/undraw_shopping_app_flsj.png")),
    COMMUNITY(Map.of(Locale.ENGLISH, "COMMUNITY",Locale.KOREA, "커뮤니티"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "https://repill-dev.s3.ap-northeast-2.amazonaws.com/undraw_People_re_8spw.png"))
    ;

    private Map<Locale, String> displayText;
    private Map<Locale, String> suggestion;

    FavoriteCategory(Map<Locale, String> displayText, Map<Locale, String> suggestion) {
        this.displayText = displayText;
        this.suggestion = suggestion;
    }

    public static List<FavoriteCategory> allCases() {
        return List.of(
                DAILY_LIFE,
                EXERCISE,
                HEALTH,
                FINANCIAL_TECHNOLOGY,
                HOBBY,
                TRAVEL,
                RESTAURANT_CAFE,
                SHOPPING,
                COMMUNITY
        );
    }

    public String getDisplayText(Locale locale) {
        return displayText.get(locale);
    }

    public String getSuggestion(Locale locale) {
        return suggestion.get(locale);
    }

    public static void validateCategory(String category) {
        List<FavoriteCategory> favoriteCategories = allCases();
        List<FavoriteCategory> collect = favoriteCategories.stream().filter(one -> one.name().equals(category)).collect(Collectors.toList());
        if(collect.isEmpty()) {
            throw new CategoryNotFoundException();
        }
    }
}
