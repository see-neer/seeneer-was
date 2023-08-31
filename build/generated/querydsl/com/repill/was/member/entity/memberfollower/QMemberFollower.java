package com.repill.was.member.entity.memberfollower;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberFollower is a Querydsl query type for MemberFollower
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberFollower extends EntityPathBase<MemberFollower> {

    private static final long serialVersionUID = 609041021L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberFollower memberFollower = new QMemberFollower("memberFollower");

    public final DateTimePath<java.time.ZonedDateTime> createdAt = createDateTime("createdAt", java.time.ZonedDateTime.class);

    public final com.repill.was.member.entity.member.QMemberId followedId;

    public final com.repill.was.member.entity.member.QMemberId followerId;

    public final QMemberFollowerId memberFollowerId;

    public QMemberFollower(String variable) {
        this(MemberFollower.class, forVariable(variable), INITS);
    }

    public QMemberFollower(Path<? extends MemberFollower> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberFollower(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberFollower(PathMetadata metadata, PathInits inits) {
        this(MemberFollower.class, metadata, inits);
    }

    public QMemberFollower(Class<? extends MemberFollower> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.followedId = inits.isInitialized("followedId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("followedId")) : null;
        this.followerId = inits.isInitialized("followerId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("followerId")) : null;
        this.memberFollowerId = inits.isInitialized("memberFollowerId") ? new QMemberFollowerId(forProperty("memberFollowerId")) : null;
    }

}

