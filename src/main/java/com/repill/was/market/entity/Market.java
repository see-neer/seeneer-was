package com.repill.was.market.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Market {

    @EmbeddedId
    MarketId id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imageSrc;

    @Column(columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    private String address;

    @Column(columnDefinition = "VARCHAR(50)")
    private String date;
}