package com.repill.was.global.model;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {

    boolean sameValueAs(T other);
}
