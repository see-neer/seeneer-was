package com.repill.was.global.shard.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SingleDataResponse<T> {
    private T data;

    public SingleDataResponse(T data) {
        this.data = data;
    }
}
