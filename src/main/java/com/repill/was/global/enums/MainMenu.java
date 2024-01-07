package com.repill.was.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MainMenu {
    MY_PAGE("내 정보", null, true),
    MARKET("전국 장날", null,true),
    COMMUNITY("이야기 방", "이모 저모", true),
    FESTIVAL("축제/행사", null, false);

    private final String description;
    private final String subDescription;
    private final Boolean isOpen;
}
