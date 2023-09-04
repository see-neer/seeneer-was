package com.repill.was.member.entity.recentlyviewditem;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecentlyViewedItem is a Querydsl query type for RecentlyViewedItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecentlyViewedItem extends EntityPathBase<RecentlyViewedItem> {

    private static final long serialVersionUID = -878277420L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecentlyViewedItem recentlyViewedItem = new QRecentlyViewedItem("recentlyViewedItem");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final QRecentlyViewedItemId id;

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final EnumPath<RecentlyViewedItem.ItemType> itemType = createEnum("itemType", RecentlyViewedItem.ItemType.class);

    public final com.repill.was.member.entity.member.QMemberId memberId;

    public QRecentlyViewedItem(String variable) {
        this(RecentlyViewedItem.class, forVariable(variable), INITS);
    }

    public QRecentlyViewedItem(Path<? extends RecentlyViewedItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecentlyViewedItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecentlyViewedItem(PathMetadata metadata, PathInits inits) {
        this(RecentlyViewedItem.class, metadata, inits);
    }

    public QRecentlyViewedItem(Class<? extends RecentlyViewedItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QRecentlyViewedItemId(forProperty("id")) : null;
        this.memberId = inits.isInitialized("memberId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("memberId")) : null;
    }

}

