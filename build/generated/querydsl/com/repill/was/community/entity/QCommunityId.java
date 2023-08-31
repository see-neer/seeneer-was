package com.repill.was.community.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommunityId is a Querydsl query type for CommunityId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCommunityId extends BeanPath<CommunityId> {

    private static final long serialVersionUID = -1404326760L;

    public static final QCommunityId communityId = new QCommunityId("communityId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCommunityId(String variable) {
        super(CommunityId.class, forVariable(variable));
    }

    public QCommunityId(Path<? extends CommunityId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunityId(PathMetadata metadata) {
        super(CommunityId.class, metadata);
    }

}

