package com.repill.was.review.query.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.repill.was.global.model.ImageListData;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewVO {

    private Long id;
    private Long itemId;
    private ImageListData imageSrc;
    private LocalDateTime date;
    private String content;

    @QueryProjection
    public ReviewVO(Long id, Long itemId, ImageListData imageSrc, LocalDateTime date, String content) {
        this.id = id;
        this.itemId = itemId;
        this.imageSrc = imageSrc;
        this.date = date;
        this.content = content;
    }
}
