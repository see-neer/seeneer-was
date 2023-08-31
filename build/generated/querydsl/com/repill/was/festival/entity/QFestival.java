package com.repill.was.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFestival is a Querydsl query type for Festival
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFestival extends EntityPathBase<Festival> {

    private static final long serialVersionUID = -731032313L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFestival festival = new QFestival("festival");

    public final StringPath address = createString("address");

    public final StringPath date = createString("date");

    public final QFestivalId id;

    public final StringPath imageSrc = createString("imageSrc");

    public final StringPath name = createString("name");

    public QFestival(String variable) {
        this(Festival.class, forVariable(variable), INITS);
    }

    public QFestival(Path<? extends Festival> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFestival(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFestival(PathMetadata metadata, PathInits inits) {
        this(Festival.class, metadata, inits);
    }

    public QFestival(Class<? extends Festival> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QFestivalId(forProperty("id")) : null;
    }

}

