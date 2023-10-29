package com.repill.was.member.entity.memberCloseHistory;

import com.repill.was.global.enums.ClosingAccountReason;
import com.repill.was.global.model.EntityListData;
import com.repill.was.global.model.EntityListDataConverter;
import com.repill.was.member.entity.member.MemberId;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
public class MemberCloseHistory {
    @EmbeddedId
    private MemberId memberId; // 탈퇴 기록시 memberId를 pk로 사용

    @Convert(converter = EntityListDataConverter.class)
    @Column(columnDefinition = "VARCHAR(100)")
    private EntityListData type;

    @Column(columnDefinition = "TEXT")
    private String additionalInformation;

    public static MemberCloseHistory newOne(MemberId memberId, EntityListData closingAccountReason, String additionalInformation) {
        MemberCloseHistory history = new MemberCloseHistory();
        history.memberId = memberId;
        history.type = closingAccountReason;
        history.additionalInformation = additionalInformation;
        return history;
    }
}
