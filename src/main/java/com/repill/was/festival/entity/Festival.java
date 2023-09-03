package com.repill.was.festival.entity;

import com.repill.was.market.entity.Market;
import com.repill.was.market.entity.MarketId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
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

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public Festival(FestivalId id, String imageSrc, String name, String address, String date, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.imageSrc = imageSrc;
        this.name = name;
        this.address = address;
        this.date = date;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Festival newOne(FestivalId festivalId, String imageSrc, String name, String address, String date) {
        return new Festival(
                festivalId,
                imageSrc,
                name,
                address,
                date,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}