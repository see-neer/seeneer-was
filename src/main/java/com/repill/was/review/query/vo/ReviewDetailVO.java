package com.repill.was.review.query.vo;

import com.querydsl.core.annotations.QueryProjection;
import com.repill.was.global.model.EntityListData;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewDetailVO {

    private Long id;
    private Long itemId;
    private EntityListData imageSrc;
    private LocalDateTime date;
    private String content;
    private String title;

    @QueryProjection
    public ReviewDetailVO(Long id, Long itemId, EntityListData imageSrc, LocalDateTime date, String content, String title) {
        this.id = id;
        this.itemId = itemId;
        this.imageSrc = imageSrc;
        this.date = date;
        this.content = content;
        this.title = title;
    }
}
