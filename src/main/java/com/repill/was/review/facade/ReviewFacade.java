package com.repill.was.review.facade;

import com.repill.was.global.shard.enums.ItemType;
import com.repill.was.member.controller.dto.response.view.MemberView;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.review.controller.dto.response.ReviewDetailResponse;
import com.repill.was.review.controller.dto.response.ReviewListResponse;
import com.repill.was.review.query.ReviewQueries;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewFacade {

    private final ReviewQueries reviewQueries;

    public List<ReviewListResponse> getLists(MemberId memberId, ItemType itemType, Long cursorId, int size) {
        List<ReviewVO> list = reviewQueries.getList(memberId, itemType, cursorId, size);
        return list.stream().map(one -> {
            return ReviewListResponse.from(one, 5, 10);
        }).collect(Collectors.toList());
    }

    public ReviewDetailResponse getReviewDetail(Long id, Long itemId, ItemType itemType) {
        ReviewDetailVO reviewDetail = reviewQueries.getReviewDetail(id, itemId, itemType);
        return ReviewDetailResponse.from(reviewDetail, 4.3, 10, reviewDetail.getDate(), new ReviewDetailResponse.Comment(1L, new MemberView(), "@2", "@2", null));
    }
}
