package com.repill.was.market.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMarketReviewId is a Querydsl query type for MarketReviewId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMarketReviewId extends BeanPath<MarketReviewId> {

    private static final long serialVersionUID = -89058942L;

    public static final QMarketReviewId marketReviewId = new QMarketReviewId("marketReviewId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMarketReviewId(String variable) {
        super(MarketReviewId.class, forVariable(variable));
    }

    public QMarketReviewId(Path<? extends MarketReviewId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMarketReviewId(PathMetadata metadata) {
        super(MarketReviewId.class, metadata);
    }

}

