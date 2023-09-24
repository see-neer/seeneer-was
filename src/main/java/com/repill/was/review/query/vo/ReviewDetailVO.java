package com.repill.was.review.query.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewDetailVO {

    private Long id;
    private String imageSrc;
    private LocalDateTime date;
    private String content;
    private String title;

    @QueryProjection
    public ReviewDetailVO(Long id, String imageSrc, LocalDateTime date, String content, String title) {
        this.id = id;
        this.imageSrc = imageSrc;
        this.date = date;
        this.content = content;
        this.title = title;
    }
}
