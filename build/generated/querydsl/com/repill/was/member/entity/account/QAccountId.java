package com.repill.was.member.entity.account;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccountId is a Querydsl query type for AccountId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAccountId extends BeanPath<AccountId> {

    private static final long serialVersionUID = -1091781226L;

    public static final QAccountId accountId = new QAccountId("accountId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAccountId(String variable) {
        super(AccountId.class, forVariable(variable));
    }

    public QAccountId(Path<? extends AccountId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccountId(PathMetadata metadata) {
        super(AccountId.class, metadata);
    }

}

