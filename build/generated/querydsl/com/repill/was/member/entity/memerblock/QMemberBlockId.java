package com.repill.was.member.entity.memerblock;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberBlockId is a Querydsl query type for MemberBlockId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberBlockId extends BeanPath<MemberBlockId> {

    private static final long serialVersionUID = 2106129244L;

    public static final QMemberBlockId memberBlockId = new QMemberBlockId("memberBlockId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMemberBlockId(String variable) {
        super(MemberBlockId.class, forVariable(variable));
    }

    public QMemberBlockId(Path<? extends MemberBlockId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberBlockId(PathMetadata metadata) {
        super(MemberBlockId.class, metadata);
    }

}

