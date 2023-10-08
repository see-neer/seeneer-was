package com.repill.was.item.entity;

import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.model.ImageListData;
import com.repill.was.global.model.ImageListDataConverter;
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

    @Convert(converter = ImageListDataConverter.class)
    @Column(columnDefinition = "TEXT")
    private ImageListData images;

    @Column(columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    private String address;

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

    public Festival(FestivalId id, ImageListData images, String name, String address, LocalDateTime date, boolean isClosed, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.images = images;
        this.name = name;
        this.address = address;
        this.date = date;
        this.isClosed = isClosed;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public static Festival newOne(FestivalId festivalId, ImageListData images, String name, String address, LocalDateTime date) {
        return new Festival(
                festivalId,
                images,
                name,
                address,
                date,
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public void addLike(MemberLike memberLike) {
        if(!memberLike.getItemId().equals(this.id.getId())) throw new BadRequestException("좋아요 실패");
        this.likeCount += 1;
    }

    private boolean isActive(LocalDateTime dateTime) {
        if(this.date.isBefore(dateTime) && !this.isClosed) {
            return true;
        }
        return false;
    }
}