package com.repill.was.member.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLoginRequest {
    private String userId;
    private String userPassword;
    private String accessToken;
}
