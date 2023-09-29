package com.repill.was.item.entity;

import com.repill.was.global.model.ImageListData;
import com.repill.was.global.model.ImageListDataConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Market {
    @EmbeddedId
    MarketId id;

    @Convert(converter = ImageListDataConverter.class)
    @Column(columnDefinition = "TEXT")
    private ImageListData images;

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

    public Market(MarketId id, ImageListData images, String name, String address, String date, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.images = images;
        this.name = name;
        this.address = address;
        this.date = date;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Market newOne(MarketId marketId, ImageListData images, String name, String address, String date) {
        return new Market(
                marketId,
                images,
                name,
                address,
                date,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}