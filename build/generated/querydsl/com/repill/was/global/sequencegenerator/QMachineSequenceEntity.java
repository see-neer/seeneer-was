package com.repill.was.global.sequencegenerator;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMachineSequenceEntity is a Querydsl query type for MachineSequenceEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMachineSequenceEntity extends EntityPathBase<MachineSequenceEntity> {

    private static final long serialVersionUID = -283048514L;

    public static final QMachineSequenceEntity machineSequenceEntity = new QMachineSequenceEntity("machineSequenceEntity");

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public QMachineSequenceEntity(String variable) {
        super(MachineSequenceEntity.class, forVariable(variable));
    }

    public QMachineSequenceEntity(Path<? extends MachineSequenceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMachineSequenceEntity(PathMetadata metadata) {
        super(MachineSequenceEntity.class, metadata);
    }

}

