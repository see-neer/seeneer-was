package com.repill.was.market.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarketReview is a Querydsl query type for MarketReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarketReview extends EntityPathBase<MarketReview> {

    private static final long serialVersionUID = 773090823L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarketReview marketReview = new QMarketReview("marketReview");

    public final StringPath contents = createString("contents");

    public final DateTimePath<java.time.ZonedDateTime> createdAt = createDateTime("createdAt", java.time.ZonedDateTime.class);

    public final SetPath<MarketReview.Emoji, EnumPath<MarketReview.Emoji>> emojis = this.<MarketReview.Emoji, EnumPath<MarketReview.Emoji>>createSet("emojis", MarketReview.Emoji.class, EnumPath.class, PathInits.DIRECT2);

    public final QMarketReviewId id;

    public final StringPath imageSrc = createString("imageSrc");

    public final QMarketId marketId;

    public final com.repill.was.member.entity.member.QMemberId memberId;

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final DateTimePath<java.time.ZonedDateTime> updatedAt = createDateTime("updatedAt", java.time.ZonedDateTime.class);

    public final DateTimePath<java.time.ZonedDateTime> visitedAt = createDateTime("visitedAt", java.time.ZonedDateTime.class);

    public QMarketReview(String variable) {
        this(MarketReview.class, forVariable(variable), INITS);
    }

    public QMarketReview(Path<? extends MarketReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarketReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarketReview(PathMetadata metadata, PathInits inits) {
        this(MarketReview.class, metadata, inits);
    }

    public QMarketReview(Class<? extends MarketReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QMarketReviewId(forProperty("id")) : null;
        this.marketId = inits.isInitialized("marketId") ? new QMarketId(forProperty("marketId")) : null;
        this.memberId = inits.isInitialized("memberId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("memberId")) : null;
    }

}

