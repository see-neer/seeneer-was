package com.repill.was.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 974438237L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final ListPath<Comment, QComment> child = this.<Comment, QComment>createList("child", Comment.class, QComment.class, PathInits.DIRECT2);

    public final com.repill.was.member.entity.member.QMemberId commentMemberId;

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.time.ZonedDateTime> createdAt = createDateTime("createdAt", java.time.ZonedDateTime.class);

    public final QCommentId id;

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final NumberPath<Integer> parentOrderId = createNumber("parentOrderId", Integer.class);

    public final QComment parents;

    public final com.repill.was.member.entity.member.QMemberId postMemberId;

    public final DateTimePath<java.time.ZonedDateTime> updatedAt = createDateTime("updatedAt", java.time.ZonedDateTime.class);

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.commentMemberId = inits.isInitialized("commentMemberId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("commentMemberId")) : null;
        this.id = inits.isInitialized("id") ? new QCommentId(forProperty("id")) : null;
        this.parents = inits.isInitialized("parents") ? new QComment(forProperty("parents"), inits.get("parents")) : null;
        this.postMemberId = inits.isInitialized("postMemberId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("postMemberId")) : null;
    }

}

