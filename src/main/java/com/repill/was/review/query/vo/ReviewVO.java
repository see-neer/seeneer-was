package com.repill.was.review.query.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewVO {

    private Long id;
    private Long itemId;
    private String imageSrc;
    private LocalDateTime date;
    private String content;

    @QueryProjection
    public ReviewVO(Long id, Long itemId, String imageSrc, LocalDateTime date, String content) {
        this.id = id;
        this.itemId = itemId;
        this.imageSrc = imageSrc;
        this.date = date;
        this.content = content;
    }
}
