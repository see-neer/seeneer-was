package com.repill.was.global.shard.infra.dto.response;

import com.repill.was.member.controller.dto.response.view.MemberView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HealthCheckResponse {

    @ApiModelProperty(notes = "현재 멤버 상태, false 인 경우 대기 상태이며, weWootApplication field 존재함.")
    private boolean member;

    @ApiModelProperty(notes = "거주지 승인 완료일 경우 wewoot 정보, isMember false 일경우 empty list")
    private MemberView memberView;

    @ApiModelProperty(notes = "accessToken 신규 발급")
    private String accessToken;

    public HealthCheckResponse(boolean member, MemberView memberView, String accessToken) {
        this.member = member;
        this.memberView = memberView;
        this.accessToken = accessToken;
    }
}
