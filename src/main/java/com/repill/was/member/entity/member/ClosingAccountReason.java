package com.repill.was.member.entity.member;

import lombok.Getter;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Getter
public enum ClosingAccountReason {

    CAR_NOT_USEFUL(Map.of(Locale.ENGLISH, "not useful",Locale.KOREA, "이용하고 싶은 서비스가 없어요"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    CAR_DEAL_UNSUCCESSFUL(Map.of(Locale.ENGLISH, "deal unsuccessful",Locale.KOREA, "거래가 성사되지 않아요"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    CAR_BAD_USER(Map.of(Locale.ENGLISH, "met bad user",Locale.KOREA, "비매너 ‘We웃’을 만났어요"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    CAR_NEED_NEW_ACCOUNT(Map.of(Locale.ENGLISH, "need to create new account",Locale.KOREA, "새 계정을 만들고 싶어요"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    CAR_MOVE(Map.of(Locale.ENGLISH, "moving or phone number changed",Locale.KOREA, "이사를 가요 혹은 전화번호가 바뀌었어요"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    CAR_VERIFICATION_DIFFICULT(Map.of(Locale.ENGLISH, "inconvenient verification process",Locale.KOREA, "실거주 인증이 불편해요 (개인정보 노출 등)"),
            Map.of(Locale.ENGLISH, "",Locale.KOREA, "")),
    CAR_INCONVENIENT_FEATURE(Map.of(Locale.ENGLISH, "some features are inconvenient",Locale.KOREA, "특정 기능이 불편해요"),
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
                CAR_NOT_USEFUL,
                CAR_DEAL_UNSUCCESSFUL,
                CAR_BAD_USER,
                CAR_NEED_NEW_ACCOUNT,
                CAR_MOVE,
                CAR_VERIFICATION_DIFFICULT,
                CAR_INCONVENIENT_FEATURE
        );
    }

    public String getDisplayText(Locale locale) {
        return displayText.get(locale);
    }

    public String getSuggestion(Locale locale) {
        return suggestion.get(locale);
    }
}
