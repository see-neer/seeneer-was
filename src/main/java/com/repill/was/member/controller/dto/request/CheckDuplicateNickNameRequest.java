package com.repill.was.member.controller.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckDuplicateNickNameRequest {

    @ApiModelProperty(notes = "중복 확인 닉네임")
    private String insertedNickname;
    @ApiModelProperty(notes = "카카오 닉네임 확인 여부")
    private Boolean useKakaoNickname;
}
