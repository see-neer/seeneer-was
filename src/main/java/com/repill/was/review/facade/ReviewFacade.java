package com.repill.was.review.facade;

import com.repill.was.global.enums.ItemType;
import com.repill.was.global.utils.PageUtils;
import com.repill.was.member.controller.dto.response.view.MemberView;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.review.controller.dto.response.ReviewDetailResponse;
import com.repill.was.review.controller.dto.response.ReviewListResponse;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;
import com.repill.was.global.factory.itemvalidate.ItemValidateFactory;
import com.repill.was.global.factory.itemvalidate.ItemValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.repill.was.global.enums.Sort.CREATED_AT;

@Service
@RequiredArgsConstructor
public class ReviewFacade {
    private final ItemValidateFactory itemValidateFactory;

    public Page<ReviewListResponse> getReviewLists(MemberId memberId, ItemType itemType, int page, int size) {
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        List<ReviewVO> list = validatorBy.getReviewList(memberId);
        List<ReviewListResponse> collect = list.stream().map(one -> {
            return ReviewListResponse.from(one, itemType, 5, 10);
        }).collect(Collectors.toList());
        return PageUtils.makePage(collect, CREATED_AT.name(), size, page);
    }

    public ReviewDetailResponse getReviewDetail(Long id, Long itemId, ItemType itemType) {
        ItemValidator validatorBy = itemValidateFactory.getValidatorBy(itemType);
        ReviewDetailVO reviewDetail = validatorBy.getReviewDetailList(id, itemId);
        return ReviewDetailResponse.from(reviewDetail, 4.3, 10, reviewDetail.getDate(), new ReviewDetailResponse.Comment(1L, new MemberView(), "@2", "@2", null));
    }
}
