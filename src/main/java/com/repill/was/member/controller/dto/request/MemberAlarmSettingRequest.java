package com.repill.was.member.controller.dto.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class MemberAlarmSettingRequest {

    @ApiModelProperty(required = true, notes = "마케팅 수신 알림")
    @NotNull
    private boolean marketingTermsAgreed;
    @ApiModelProperty(required = true, notes = "업데이트 알림")
    @NotNull
    private boolean appUpdateNotificationEnabled;
    @ApiModelProperty(required = true, notes = "후기 댓글 알림")
    @NotNull
    private boolean reviewCommentNotificationEnabled;
    @ApiModelProperty(required = true, notes = "공지사항 알림")
    @NotNull
    private boolean serviceNoticeNotificationEnabled;
}
