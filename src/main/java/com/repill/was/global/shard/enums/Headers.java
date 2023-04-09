package com.repill.was.global.shard.enums;

import lombok.Getter;

@Getter
public enum Headers {
    X_ID_VERIFY_RESULT("X-ID-VERIFY-RESULT"),
    AUTHORIZATION("Authorization");

    private String key;

    Headers(String key) { this.key = key; }
}
