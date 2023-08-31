package com.repill.was.member.entity.memerreport;


import lombok.Getter;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Getter
public enum MemberReportType {
    MRT_NO_SHOW(Map.of(Locale.ENGLISH, "No Show",Locale.KOREA, "게시글 거래에 대한 No-Show")),
    MRT_BAD_CONVO(Map.of(Locale.ENGLISH, "Bad Conversation",Locale.KOREA, "특정 인물 지목 혹은 불쾌한 대화 유도")),
    MRT_BAD_LANG(Map.of(Locale.ENGLISH, "Bad Language",Locale.KOREA, "욕설, 비속어, 인신 공격, 부적절한 언어 사용")),
    MRT_BAD_CONTENTS(Map.of(Locale.ENGLISH, "Advertisement and meaningless contents",Locale.KOREA, "광고 및 무의미한 대화 글 도배")),
    MRT_PRIVACY_CRIME(Map.of(Locale.ENGLISH, "Crime of Privacy",Locale.KOREA, "사생활 침해(다른 사람 주소 공개, 전화번호 공개 등)")),
    MRT_SEXUAL_CONTENTS(Map.of(Locale.ENGLISH, "Sexual Contents",Locale.KOREA, "성희롱 및 성적 수치심을 유발 할 수 있는 모든 내용")),
    MRT_FRAUD(Map.of(Locale.ENGLISH, "Fraud attempt",Locale.KOREA, "금전 요구 및 사기성 대화 시도")),
    ;
    private Map<Locale, String> displayText;

    MemberReportType(Map<Locale, String> displayText) {
        this.displayText = displayText;
    }

    public static List<MemberReportType> allTypes() {
        return List.of(MRT_NO_SHOW, MRT_BAD_CONVO, MRT_BAD_LANG, MRT_BAD_CONTENTS, MRT_PRIVACY_CRIME, MRT_SEXUAL_CONTENTS, MRT_FRAUD);
    }

    public String configureReason(Locale locale) {
        return Optional.ofNullable(this.displayText.get(locale)).orElse("");
    }

    @Override
    public String toString() {
        return name();
    }
}

