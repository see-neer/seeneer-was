package com.repill.was.market.entity;

public interface MarketReviewRepository {
    MarketReviewId nextId();

    MarketReview save(MarketReview marketReview);
}
