package com.repill.was.global.shard.infra.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AutoLoginRequest {
    @ApiModelProperty(required = true, notes = "OS 종류 (IOS, ANDROID)")
    @NotNull
    private String osType;

    @ApiModelProperty(notes = "기기 고유 id")
    private String deviceId;

    public AutoLoginRequest(String osType, String deviceId) {
        this.osType = osType;
        this.deviceId = deviceId;
    }
}
