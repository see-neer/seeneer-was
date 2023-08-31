package com.repill.was.member.entity.memberfavoritemarket;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberFavoriteMarket is a Querydsl query type for MemberFavoriteMarket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberFavoriteMarket extends EntityPathBase<MemberFavoriteMarket> {

    private static final long serialVersionUID = -1932644387L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberFavoriteMarket memberFavoriteMarket = new QMemberFavoriteMarket("memberFavoriteMarket");

    public final DateTimePath<java.time.ZonedDateTime> createdAt = createDateTime("createdAt", java.time.ZonedDateTime.class);

    public final QMemberFavoriteMarketId id;

    public final com.repill.was.market.entity.QMarketId marketId;

    public final com.repill.was.member.entity.member.QMemberId memberId;

    public QMemberFavoriteMarket(String variable) {
        this(MemberFavoriteMarket.class, forVariable(variable), INITS);
    }

    public QMemberFavoriteMarket(Path<? extends MemberFavoriteMarket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberFavoriteMarket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberFavoriteMarket(PathMetadata metadata, PathInits inits) {
        this(MemberFavoriteMarket.class, metadata, inits);
    }

    public QMemberFavoriteMarket(Class<? extends MemberFavoriteMarket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QMemberFavoriteMarketId(forProperty("id")) : null;
        this.marketId = inits.isInitialized("marketId") ? new com.repill.was.market.entity.QMarketId(forProperty("marketId")) : null;
        this.memberId = inits.isInitialized("memberId") ? new com.repill.was.member.entity.member.QMemberId(forProperty("memberId")) : null;
    }

}

