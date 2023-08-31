package com.repill.was.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFestivalId is a Querydsl query type for FestivalId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFestivalId extends BeanPath<FestivalId> {

    private static final long serialVersionUID = 1852586114L;

    public static final QFestivalId festivalId = new QFestivalId("festivalId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QFestivalId(String variable) {
        super(FestivalId.class, forVariable(variable));
    }

    public QFestivalId(Path<? extends FestivalId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFestivalId(PathMetadata metadata) {
        super(FestivalId.class, metadata);
    }

}

