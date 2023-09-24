package com.repill.was.review.entity;

import com.repill.was.festival.entity.Festival;
import com.repill.was.festival.entity.FestivalId;
import com.repill.was.global.shard.enums.ItemType;
import com.repill.was.market.entity.MarketId;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    ReviewId nextId();

    Review save(Review Review);

    void delete(Review Review);

    Optional<Review> findById(ReviewId ReviewId);

    List<ReviewVO> getMarketList(MemberId memberId, Long cursorId, int size);

    List<ReviewVO> getFestivalList(MemberId memberId, Long cursorId, int size);

    ReviewDetailVO getMarketReviewDetail(ReviewId reviewId, MarketId marketId);
    ReviewDetailVO getFestivalReviewDetail(ReviewId reviewId, FestivalId festivalId);
}
