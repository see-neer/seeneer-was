package com.repill.was.member.entity.memerblock;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberBlock is a Querydsl query type for MemberBlock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberBlock extends EntityPathBase<MemberBlock> {

    private static final long serialVersionUID = 632358497L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberBlock memberBlock = new QMemberBlock("memberBlock");

    public final DateTimePath<java.time.ZonedDateTime> createdAt = createDateTime("createdAt", java.time.ZonedDateTime.class);

    public final QMemberBlockId id;

    public final com.repill.was.member.entity.member.QMemberId memberId;

    public final com.repill.was.member.entity.member.QMemberId targetMemberId;

    public QMemberBlock(String variable) {
        this(MemberBlock.class, forVariable(variable), INITS);
    }

    public QMemberBlock(Path<? extends MemberBlock> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberBlock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberBlock(PathMetadata metadata, PathInits inits) {
        this(MemberBlock.class, metadata, inits);
    }

    public QMemberBlock(Class<? extends MemberBlock> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QMemberBlockId(forProperty("id")) : null;
        this.memberId = inits.isInitialized("memberId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("memberId")) : null;
        this.targetMemberId = inits.isInitialized("targetMemberId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("targetMemberId")) : null;
    }

}

