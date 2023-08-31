package com.repill.was.member.entity.memerreport;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberReport is a Querydsl query type for MemberReport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberReport extends EntityPathBase<MemberReport> {

    private static final long serialVersionUID = 199084951L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberReport memberReport = new QMemberReport("memberReport");

    public final StringPath additionalInformation = createString("additionalInformation");

    public final DateTimePath<java.time.ZonedDateTime> createdAt = createDateTime("createdAt", java.time.ZonedDateTime.class);

    public final QMemberReportId id;

    public final com.repill.was.member.entity.member.QMemberId memberId;

    public final com.repill.was.member.entity.member.QMemberId reporterId;

    public final EnumPath<MemberReportType> type = createEnum("type", MemberReportType.class);

    public QMemberReport(String variable) {
        this(MemberReport.class, forVariable(variable), INITS);
    }

    public QMemberReport(Path<? extends MemberReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberReport(PathMetadata metadata, PathInits inits) {
        this(MemberReport.class, metadata, inits);
    }

    public QMemberReport(Class<? extends MemberReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QMemberReportId(forProperty("id")) : null;
        this.memberId = inits.isInitialized("memberId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("memberId")) : null;
        this.reporterId = inits.isInitialized("reporterId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("reporterId")) : null;
    }

}

