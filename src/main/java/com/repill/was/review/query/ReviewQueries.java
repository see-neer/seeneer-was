package com.repill.was.review.query;

import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.entity.MarketId;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.review.entity.ReviewId;
import com.repill.was.review.entity.ReviewRepository;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueries {
    private final ReviewRepository reviewRepository;

    public List<ReviewVO> getMarketReviewLists(MemberId memberId) {
        return reviewRepository.getMarketReviewLists(memberId);
    }

    public List<ReviewVO> getFestivalReviewLists(MemberId memberId) {
        return reviewRepository.getFestivalReviewLists(memberId);
    }

    public ReviewDetailVO getMarketReviewDetail(Long id, Long itemId) {
        return reviewRepository.getMarketReviewDetail(new ReviewId(id), new MarketId(itemId));
    }

    public ReviewDetailVO getFestivalReviewDetail(Long id, Long itemId) {
        return reviewRepository.getFestivalReviewDetail(new ReviewId(id), new FestivalId(itemId));
    }
}
