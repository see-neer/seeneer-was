package com.repill.was.member.controller.dto.response;

import com.repill.was.member.entity.member.Member;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberAlarmSettingResponse {

    private boolean marketingTermsAgreed;
    private boolean appUpdateNotificationEnabled;
    private boolean reviewCommentNotificationEnabled;
    private boolean serviceNoticeNotificationEnabled;

    public MemberAlarmSettingResponse(boolean marketingTermsAgreed, boolean appUpdateNotificationEnabled, boolean reviewCommentNotificationEnabled, boolean serviceNoticeNotificationEnabled) {
        this.marketingTermsAgreed = marketingTermsAgreed;
        this.appUpdateNotificationEnabled = appUpdateNotificationEnabled;
        this.reviewCommentNotificationEnabled = reviewCommentNotificationEnabled;
        this.serviceNoticeNotificationEnabled = serviceNoticeNotificationEnabled;
    }

    public static MemberAlarmSettingResponse from(Member member) {
        return new MemberAlarmSettingResponse(
                member.getMemberSetting().isMarketingTermsAgreed(),
                member.getMemberSetting().isAppUpdateNotificationEnabled(),
                member.getMemberSetting().isReviewCommentNotificationEnabled(),
                member.getMemberSetting().isServiceNoticeNotificationEnabled()
        );
    }
}
