package com.repill.was.operation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddressId is a Querydsl query type for AddressId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAddressId extends BeanPath<AddressId> {

    private static final long serialVersionUID = -958896859L;

    public static final QAddressId addressId = new QAddressId("addressId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAddressId(String variable) {
        super(AddressId.class, forVariable(variable));
    }

    public QAddressId(Path<? extends AddressId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddressId(PathMetadata metadata) {
        super(AddressId.class, metadata);
    }

}

