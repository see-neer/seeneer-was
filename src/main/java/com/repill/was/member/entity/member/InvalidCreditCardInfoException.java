package com.repill.was.member.entity.member;

import com.repill.was.global.exception.BadRequestException;

public class InvalidCreditCardInfoException extends BadRequestException {
    public InvalidCreditCardInfoException(String message) {
        super(message);
    }
}