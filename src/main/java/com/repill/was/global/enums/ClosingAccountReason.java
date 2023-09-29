package com.repill.was.global.enums;

import lombok.Getter;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Getter
public enum ClosingAccountReason {

    WORRIED_PERSONAL_INFORMATION_EXPOSURE(Map.of(Locale.ENGLISH, "WORRIED_PERSONAL_INFORMATION_EXPOSURE",Locale.KOREA, "개인정보 노출이 걱정돼요"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    NOT_USE_FREQUENTLY(Map.of(Locale.ENGLISH, "NOT_USE_FREQUENTLY",Locale.KOREA, "자주 사용하지 않아요"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    NOTIFICATION_TOO_FREQUENTLY(Map.of(Locale.ENGLISH, "NOTIFICATION_TOO_FREQUENTLY",Locale.KOREA, "알림이 너무 자주 와요"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    CONTENT_RELIABILITY_LOW(Map.of(Locale.ENGLISH, "CONTENT_RELIABILITY_LOW",Locale.KOREA, "콘텐츠 신뢰도가 낮아요"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    USING_APP_DIFFICULT(Map.of(Locale.ENGLISH, "USING_APP_DIFFICULT",Locale.KOREA, "앱 이용이 어려워요"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    OTHER(Map.of(Locale.ENGLISH, "OTHER",Locale.KOREA, "기타"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, ""))
    ;

    private Map<Locale, String> displayText;
    private Map<Locale, String> suggestion;

    ClosingAccountReason(Map<Locale, String> displayText, Map<Locale, String> suggestion) {
        this.displayText = displayText;
        this.suggestion = suggestion;
    }

    public static List<ClosingAccountReason> allCases() {
        return List.of(
                WORRIED_PERSONAL_INFORMATION_EXPOSURE,
                NOT_USE_FREQUENTLY,
                NOTIFICATION_TOO_FREQUENTLY,
                CONTENT_RELIABILITY_LOW,
                USING_APP_DIFFICULT,
                OTHER
        );
    }

    public String getDisplayText(Locale locale) {
        return displayText.get(locale);
    }

    public String getSuggestion(Locale locale) {
        return suggestion.get(locale);
    }
}
