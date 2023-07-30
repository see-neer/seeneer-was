package com.repill.was.global.shard.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {
    MY_PAGE("내 정보"),
    MARKET("전국 장날"),
    COMMUNITY("이모 저모 (이야기 방)"),
    FESTIVAL("축제/행사 [오픈 예정]");

    private final String description;
}
