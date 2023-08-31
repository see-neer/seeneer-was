package com.repill.was.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommunityCommentId is a Querydsl query type for CommunityCommentId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCommunityCommentId extends BeanPath<CommunityCommentId> {

    private static final long serialVersionUID = -1656822221L;

    public static final QCommunityCommentId communityCommentId = new QCommunityCommentId("communityCommentId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCommunityCommentId(String variable) {
        super(CommunityCommentId.class, forVariable(variable));
    }

    public QCommunityCommentId(Path<? extends CommunityCommentId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunityCommentId(PathMetadata metadata) {
        super(CommunityCommentId.class, metadata);
    }

}

