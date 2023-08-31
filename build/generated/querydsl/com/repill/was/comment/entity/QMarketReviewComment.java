package com.repill.was.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarketReviewComment is a Querydsl query type for MarketReviewComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarketReviewComment extends EntityPathBase<MarketReviewComment> {

    private static final long serialVersionUID = 458800393L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarketReviewComment marketReviewComment = new QMarketReviewComment("marketReviewComment");

    public final QComment _super;

    //inherited
    public final ListPath<Comment, QComment> child;

    // inherited
    public final com.repill.was.member.entity.member.QMemberId commentMemberId;

    //inherited
    public final StringPath contents;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt;

    public final QMarketReviewCommentId id;

    //inherited
    public final NumberPath<Integer> parentOrderId;

    // inherited
    public final QComment parents;

    // inherited
    public final com.repill.was.member.entity.member.QMemberId postMemberId;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt;

    public QMarketReviewComment(String variable) {
        this(MarketReviewComment.class, forVariable(variable), INITS);
    }

    public QMarketReviewComment(Path<? extends MarketReviewComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarketReviewComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarketReviewComment(PathMetadata metadata, PathInits inits) {
        this(MarketReviewComment.class, metadata, inits);
    }

    public QMarketReviewComment(Class<? extends MarketReviewComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QComment(type, metadata, inits);
        this.child = _super.child;
        this.commentMemberId = _super.commentMemberId;
        this.contents = _super.contents;
        this.createdAt = _super.createdAt;
        this.id = inits.isInitialized("id") ? new QMarketReviewCommentId(forProperty("id")) : null;
        this.parentOrderId = _super.parentOrderId;
        this.parents = _super.parents;
        this.postMemberId = _super.postMemberId;
        this.updatedAt = _super.updatedAt;
    }

}

