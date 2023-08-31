package com.repill.was.member.entity.memberfavoritemarket;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberFavoriteMarketId is a Querydsl query type for MemberFavoriteMarketId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberFavoriteMarketId extends BeanPath<MemberFavoriteMarketId> {

    private static final long serialVersionUID = -1845381672L;

    public static final QMemberFavoriteMarketId memberFavoriteMarketId = new QMemberFavoriteMarketId("memberFavoriteMarketId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMemberFavoriteMarketId(String variable) {
        super(MemberFavoriteMarketId.class, forVariable(variable));
    }

    public QMemberFavoriteMarketId(Path<? extends MemberFavoriteMarketId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberFavoriteMarketId(PathMetadata metadata) {
        super(MemberFavoriteMarketId.class, metadata);
    }

}

