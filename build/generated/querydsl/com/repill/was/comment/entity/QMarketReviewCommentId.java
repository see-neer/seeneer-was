package com.repill.was.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMarketReviewCommentId is a Querydsl query type for MarketReviewCommentId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMarketReviewCommentId extends BeanPath<MarketReviewCommentId> {

    private static final long serialVersionUID = -1474451452L;

    public static final QMarketReviewCommentId marketReviewCommentId = new QMarketReviewCommentId("marketReviewCommentId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMarketReviewCommentId(String variable) {
        super(MarketReviewCommentId.class, forVariable(variable));
    }

    public QMarketReviewCommentId(Path<? extends MarketReviewCommentId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMarketReviewCommentId(PathMetadata metadata) {
        super(MarketReviewCommentId.class, metadata);
    }

}

