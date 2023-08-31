package com.repill.was.festival.entity;

import com.repill.was.market.entity.MarketId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Festival {

    @EmbeddedId
    FestivalId id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imageSrc;

    @Column(columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    private String address;

    @Column(columnDefinition = "VARCHAR(50)")
    private String date;
}