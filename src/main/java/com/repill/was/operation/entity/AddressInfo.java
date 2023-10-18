package com.repill.was.operation.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class AddressInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailA;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailB;
}
