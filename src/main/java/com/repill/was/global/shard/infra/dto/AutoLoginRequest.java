package com.repill.was.global.shard.infra.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AutoLoginRequest {

    @ApiModelProperty(notes = "기기 고유 id")
    private String deviceId;
}
