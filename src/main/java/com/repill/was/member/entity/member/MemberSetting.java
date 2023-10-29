package com.repill.was.member.entity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Locale;

@Getter
@Embeddable
@NoArgsConstructor
public class MemberSetting {

    @Column(columnDefinition = "bit(1)", nullable = false)
    private boolean marketingTermsAgreed;
    @Column(columnDefinition = "bit(1)", nullable = false)
    private boolean appUpdateNotificationEnabled;
    @Column(columnDefinition = "bit(1)", nullable = false)
    private boolean reviewCommentNotificationEnabled;
    @Column(columnDefinition = "bit(1)", nullable = false)
    private boolean serviceNoticeNotificationEnabled;

    public static MemberSetting defaultSetting() {
        MemberSetting setting = new MemberSetting();
        setting.marketingTermsAgreed = true;
        setting.appUpdateNotificationEnabled = true;
        setting.reviewCommentNotificationEnabled = true;
        setting.serviceNoticeNotificationEnabled = true;
        return setting;
    }

    public static MemberSetting newOne(boolean marketingTermsAgreed, boolean appUpdateNotificationEnabled, boolean reviewCommentNotificationEnabled, boolean serviceNoticeNotificationEnabled) {
        MemberSetting setting = new MemberSetting();
        setting.marketingTermsAgreed = marketingTermsAgreed;
        setting.appUpdateNotificationEnabled = appUpdateNotificationEnabled;
        setting.reviewCommentNotificationEnabled = reviewCommentNotificationEnabled;
        setting.serviceNoticeNotificationEnabled = serviceNoticeNotificationEnabled;
        return setting;
    }
}
