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
public class Festival {

    @EmbeddedId
    FestivalId id;

    @Convert(converter = EntityListDataConverter.class)
    @Column(columnDefinition = "TEXT")
    private EntityListData images;

    @Column(columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailA;

    @Column(columnDefinition = "VARCHAR(50)")
    private String addressDetailB;

    @Column(columnDefinition = "VARCHAR(50)")
    private LocalDateTime date;

    @Column(columnDefinition = "BIT(1) default 0", nullable = false)
    private boolean isClosed;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long likeCount;
    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    public Festival(FestivalId id, EntityListData images, String name, String addressDetailA, String addressDetailB, LocalDateTime date, boolean isClosed, Long likeCount, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.images = images;
        this.name = name;
        this.addressDetailA = addressDetailA;
        this.addressDetailB = addressDetailB;
        this.date = date;
        this.isClosed = isClosed;
        this.likeCount = likeCount;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Festival newOne(FestivalId festivalId, EntityListData images, String name, String addressDetailA, String addressDetailB, LocalDateTime date) {
        return new Festival(
                festivalId,
                images,
                name,
                addressDetailA,
                addressDetailB,
                date,
                false,
                0L,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public void addLike(MemberLike memberLike) {
        if(!memberLike.getItemId().equals(this.id.getId())) throw new BadRequestException("좋아요 실패");
        this.likeCount += 1;
    }

    public void deleteLike(MemberLike memberLike) {
        if(!memberLike.getItemId().equals(this.id.getId())) throw new BadRequestException("좋아요 취소 실패");
        this.likeCount -= 1;
    }
}