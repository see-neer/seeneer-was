package com.repill.was.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Sort {
    CREATED_AT("created_at"),
    MEMBER_ID("member_id");

    private String key;

    Sort(String key) { this.key = key; }
}
