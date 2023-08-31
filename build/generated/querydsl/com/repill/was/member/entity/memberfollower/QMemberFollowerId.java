package com.repill.was.member.entity.memberfollower;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberFollowerId is a Querydsl query type for MemberFollowerId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberFollowerId extends BeanPath<MemberFollowerId> {

    private static final long serialVersionUID = 1172871288L;

    public static final QMemberFollowerId memberFollowerId = new QMemberFollowerId("memberFollowerId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMemberFollowerId(String variable) {
        super(MemberFollowerId.class, forVariable(variable));
    }

    public QMemberFollowerId(Path<? extends MemberFollowerId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberFollowerId(PathMetadata metadata) {
        super(MemberFollowerId.class, metadata);
    }

}

