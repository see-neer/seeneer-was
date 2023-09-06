package com.repill.was.member.controller.dto.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberLogoutRequest {
    @ApiModelProperty(required = true, notes = "OS 종류 (IOS, ANDROID)")
    @NotNull
    private String osType;

    @ApiModelProperty(notes = "기기 고유 id")
    private String deviceId;
}
