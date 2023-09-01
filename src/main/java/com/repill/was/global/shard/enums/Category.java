package com.repill.was.global.shard.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Category {
    MY_PAGE("내 정보", null, true),
    MARKET("전국 장날", null,true),
    COMMUNITY("이야기 방", null, true),
    FESTIVAL("축제/행사", "이모 저모", false);

    private final String description;
    private final String subDescription;
    private final Boolean isOpen;
}
