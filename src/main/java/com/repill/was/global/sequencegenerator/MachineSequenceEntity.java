package com.repill.was.global.sequencegenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "machine_sequence")
public class MachineSequenceEntity {

    @Id
    Integer sequence;
}
