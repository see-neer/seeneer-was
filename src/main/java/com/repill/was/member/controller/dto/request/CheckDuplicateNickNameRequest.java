package com.repill.was.member.controller.dto.request;

import lombok.Getter;

@Getter
public class CheckDuplicateNickNameRequest {

    private String insertedNickname;
    private Boolean isUsedSocialName;
}
