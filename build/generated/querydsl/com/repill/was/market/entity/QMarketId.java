package com.repill.was.market.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMarketId is a Querydsl query type for MarketId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMarketId extends BeanPath<MarketId> {

    private static final long serialVersionUID = -189648054L;

    public static final QMarketId marketId = new QMarketId("marketId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMarketId(String variable) {
        super(MarketId.class, forVariable(variable));
    }

    public QMarketId(Path<? extends MarketId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMarketId(PathMetadata metadata) {
        super(MarketId.class, metadata);
    }

}

