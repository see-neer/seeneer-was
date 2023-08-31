package com.repill.was.member.entity.memerreport;

import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Table(indexes = {
        @Index(columnList = "memberId, reporterId")
})
@Entity
@NoArgsConstructor
public class MemberReport {
    public static final int IMAGE_COUNT_MAX = 5;

    @EmbeddedId
    private MemberReportId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId", nullable = false))
    private MemberId memberId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "reporterId", nullable = false))
    private MemberId reporterId;

    @Column(columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private MemberReportType type;

    @Column(columnDefinition = "TEXT")
    private String additionalInformation;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private ZonedDateTime createdAt;
}
