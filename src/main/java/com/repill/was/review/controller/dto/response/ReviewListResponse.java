package com.repill.was.review.controller.dto.response;

import com.repill.was.global.shard.utils.TimeUtils;
import com.repill.was.review.query.vo.ReviewVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewListResponse {

    private Long id;
    private Long itemId;
    private String imageSrc;
    private String date;
    private String content;
    private int likeCount;
    private int commentCount;

    public ReviewListResponse(Long id, Long itemId, String imageSrc, String date, String content, int likeCount, int commentCount) {
        this.id = id;
        this.itemId = itemId;
        this.imageSrc = imageSrc;
        this.date = date;
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

    public static ReviewListResponse from(ReviewVO reviewVo, int likeCount, int commentCount) {
        return new ReviewListResponse(
                reviewVo.getId(),
                reviewVo.getItemId(),
                reviewVo.getImageSrc(),
                TimeUtils.convertToISO_8061(reviewVo.getDate()),
                reviewVo.getContent(),
                likeCount,
                commentCount
        );
    }
}
