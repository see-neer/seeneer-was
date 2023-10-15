package com.repill.was.global.enums;

import com.repill.was.operation.entity.CategoryNotFoundException;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public enum FavoriteCategory {
    DAILY_LIFE(Map.of(Locale.ENGLISH, "DAILY_LIFE",Locale.KOREA, "일상생활"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    EXERCISE(Map.of(Locale.ENGLISH, "EXERCISE",Locale.KOREA, "운동"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    HEALTH(Map.of(Locale.ENGLISH, "HEALTH",Locale.KOREA, "건강"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    FINANCIAL_TECHNOLOGY(Map.of(Locale.ENGLISH, "FINANCIAL_TECHNOLOGY",Locale.KOREA, "재태크"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    HOBBY(Map.of(Locale.ENGLISH, "HOBBY",Locale.KOREA, "취미(문화생활)"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    TRAVEL(Map.of(Locale.ENGLISH, "TRAVEL",Locale.KOREA, "여행"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    RESTAURANT_CAFE(Map.of(Locale.ENGLISH, "RESTAURANT_CAFE",Locale.KOREA, "레스토랑/카페"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    SHOPPING(Map.of(Locale.ENGLISH, "SHOPPING",Locale.KOREA, "쇼핑"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    COMMUNITY(Map.of(Locale.ENGLISH, "COMMUNITY",Locale.KOREA, "커뮤니티"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, ""))
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
