package com.repill.was.review.controller.dto.response;

import com.repill.was.global.utils.TimeUtils;
import com.repill.was.review.query.vo.ReviewVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewListResponse {

    private Long id;
    private Long itemId;
    private List<String> images;
    private String date;
    private String content;
    private int likeCount;
    private int commentCount;

    public ReviewListResponse(Long id, Long itemId, List<String> images, String date, String content, int likeCount, int commentCount) {
        this.id = id;
        this.itemId = itemId;
        this.images = images;
        this.date = date;
        this.content = content;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

    public static ReviewListResponse from(ReviewVO reviewVo, int likeCount, int commentCount) {
        return new ReviewListResponse(
                reviewVo.getId(),
                reviewVo.getItemId(),
                reviewVo.getImageSrc().getImages(),
                TimeUtils.convertToISO_8061(reviewVo.getDate()),
                reviewVo.getContent(),
                likeCount,
                commentCount
        );
    }
}
