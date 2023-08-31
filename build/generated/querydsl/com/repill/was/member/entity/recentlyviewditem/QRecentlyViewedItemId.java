package com.repill.was.member.entity.recentlyviewditem;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecentlyViewedItemId is a Querydsl query type for RecentlyViewedItemId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRecentlyViewedItemId extends BeanPath<RecentlyViewedItemId> {

    private static final long serialVersionUID = 2083959055L;

    public static final QRecentlyViewedItemId recentlyViewedItemId = new QRecentlyViewedItemId("recentlyViewedItemId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QRecentlyViewedItemId(String variable) {
        super(RecentlyViewedItemId.class, forVariable(variable));
    }

    public QRecentlyViewedItemId(Path<? extends RecentlyViewedItemId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecentlyViewedItemId(PathMetadata metadata) {
        super(RecentlyViewedItemId.class, metadata);
    }

}

