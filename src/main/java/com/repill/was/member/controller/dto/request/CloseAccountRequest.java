package com.repill.was.member.controller.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CloseAccountRequest {
    @ApiModelProperty(notes = "서비스 정책에 따른 사유: ex) CAR1")
    private List<String> type;

    @ApiModelProperty(notes = "직접 입력 사유")
    private String additionalInformation;
}
