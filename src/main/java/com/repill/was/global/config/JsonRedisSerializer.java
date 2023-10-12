package com.repill.was.global.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.repill.was.global.utils.JsonUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;

import java.lang.reflect.Type;

public class JsonRedisSerializer<T> implements RedisSerializer<T>{
    private static final byte[] EMPTY_ARRAY = new byte[0];

    public static <T> T convertValue(Object value, Class<T> clazz) {
        if (value == null) {
            return null;
        }
        return JsonUtils.convertValue(value, new TypeReference<>() {
            @Override
            public Type getType() {
                return clazz;
            }
        });
    }

    public static <T> T convertValue(Object value, TypeReference<T> typeReference) {
        if (value == null) {
            return null;
        }
        return JsonUtils.convertValue(value, typeReference);
    }

    @Override
    public byte[] serialize(T source) {
        if (source == null) {
            return EMPTY_ARRAY;
        }
        return JsonUtils.toJsonByte(source);
    }

    @Override
    public T deserialize(byte[] source) {
        if (isEmpty(source)) {
            return null;
        }
        return (T) JsonUtils.fromJson(source, Object.class);
    }

    private boolean isEmpty(@Nullable byte[] data) {
        return (data == null || data.length == 0);
    }
}

