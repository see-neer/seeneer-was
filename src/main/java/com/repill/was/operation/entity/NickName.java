package com.repill.was.operation.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class NickName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)")
    private String firstNickName;

    @Column(columnDefinition = "VARCHAR(50)")
    private String secondNickName;
}
