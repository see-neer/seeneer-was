package com.repill.was.member.entity.memerreport;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberReportId is a Querydsl query type for MemberReportId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberReportId extends BeanPath<MemberReportId> {

    private static final long serialVersionUID = -1952888046L;

    public static final QMemberReportId memberReportId = new QMemberReportId("memberReportId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMemberReportId(String variable) {
        super(MemberReportId.class, forVariable(variable));
    }

    public QMemberReportId(Path<? extends MemberReportId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberReportId(PathMetadata metadata) {
        super(MemberReportId.class, metadata);
    }

}

