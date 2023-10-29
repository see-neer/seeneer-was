package com.repill.was.item.entity;

import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.model.EntityListData;
import com.repill.was.global.model.EntityListDataConverter;
import com.repill.was.member.entity.memberLike.MemberLike;
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

    @Convert(converter = EntityListDataConverter.class)
    @Column(columnDefinition = "TEXT")
    private EntityListData images;

    @Column(columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    private String address;

    @Column(columnDefinition = "VARCHAR(50)")
    private String date;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long likeCount;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public Market(MarketId id, EntityListData images, String name, String address, String date, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.images = images;
        this.name = name;
        this.address = address;
        this.date = date;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Market newOne(MarketId marketId, EntityListData images, String name, String address, String date) {
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

    public void addLike(MemberLike memberLike) {
        if(!memberLike.getItemId().equals(this.id.getId())) throw new BadRequestException("좋아요 실패");
        this.likeCount += 1;
    }

    public void unLike(MemberLike memberLike) {
        if(!memberLike.getItemId().equals(this.id.getId())) throw new BadRequestException("좋아요 취소 실패");
        this.likeCount -= 1;
    }
}