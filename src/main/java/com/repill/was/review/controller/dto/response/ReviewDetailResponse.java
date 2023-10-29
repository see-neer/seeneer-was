package com.repill.was.review.controller.dto.response;

import com.repill.was.global.utils.TimeUtils;
import com.repill.was.member.controller.dto.response.view.MemberView;
import com.repill.was.review.entity.Review;
import com.repill.was.review.query.vo.ReviewDetailVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewDetailResponse {

    private Long id;
    private String title;
    private List<String> images;
    private String content;
    private double score;
    private int likeCount;
    private String createdAt;
    private Comment comment;

    @Getter
    @NoArgsConstructor
    public static class Comment {
        private Long id;
        private MemberView memberView;
        private String content;
        private String date;
        private Long parentId;

        public Comment(Long id, MemberView memberView, String content, String date, Long parentId) {
            this.id = id;
            this.memberView = memberView;
            this.content = content;
            this.date = date;
            this.parentId = parentId;
        }
    }


    public ReviewDetailResponse(Long id, String title, List<String> imageSrc, String content, double score, int likeCount, String createdAt, Comment comment) {
        this.id = id;
        this.title = title;
        this.images = imageSrc;
        this.content = content;
        this.score = score;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.comment = comment;
    }

    public static ReviewDetailResponse from(ReviewDetailVO reviewDetailVO, double score, int likeCount, LocalDateTime createdAt, Comment comment) {
        return new ReviewDetailResponse(
                reviewDetailVO.getId(),
                reviewDetailVO.getTitle(),
                reviewDetailVO.getImageSrc().getImages(),
                reviewDetailVO.getContent(),
                score,
                likeCount,
                TimeUtils.convertToISO_8061(reviewDetailVO.getDate()),
                comment
        );
    }
}
