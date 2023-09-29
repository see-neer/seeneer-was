package com.repill.was.global.factory;

import com.repill.was.global.enums.ItemType;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;

import java.util.List;

public interface ItemValidator {
    ItemType getSupportType();
    List<ReviewVO> getReviewList(MemberId memberId, Long cursorId, int size);

    ReviewDetailVO getReviewDetailList(Long id, Long itemId);
}
