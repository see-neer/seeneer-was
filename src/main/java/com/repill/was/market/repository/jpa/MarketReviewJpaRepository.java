package com.repill.was.market.repository.jpa;

import com.repill.was.market.entity.Market;
import com.repill.was.market.entity.MarketId;
import com.repill.was.market.entity.MarketReview;
import com.repill.was.market.entity.MarketReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketReviewJpaRepository extends JpaRepository<MarketReview, MarketReviewId> {
}
