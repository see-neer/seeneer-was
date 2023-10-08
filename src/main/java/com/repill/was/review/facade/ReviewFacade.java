package com.repill.was.review.facade;

import com.repill.was.global.enums.ItemType;
import com.repill.was.member.controller.dto.response.view.MemberView;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.review.controller.dto.response.ReviewDetailResponse;
import com.repill.was.review.controller.dto.response.ReviewListResponse;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;
import com.repill.was.global.factory.itemvalidate.ItemValidateFactory;
import com.repill.was.global.factory.itemvalidate.ItemValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewFacade {
    private final ItemValidateFactory itemValidateFactory;

    public List<ReviewListResponse> getReviewLists(MemberId memberId, ItemType itemType, Long cursorId, int size) {
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        List<ReviewVO> list = validatorBy.getReviewList(memberId, cursorId, size);
        return list.stream().map(one -> {
            return ReviewListResponse.from(one, 5, 10);
        }).collect(Collectors.toList());
    }

    public ReviewDetailResponse getReviewDetail(Long id, Long itemId, ItemType itemType) {
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        ReviewDetailVO reviewDetail = validatorBy.getReviewDetailList(id, itemId);
        return ReviewDetailResponse.from(reviewDetail, 4.3, 10, reviewDetail.getDate(), new ReviewDetailResponse.Comment(1L, new MemberView(), "@2", "@2", null));
    }
}
