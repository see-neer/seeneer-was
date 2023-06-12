package com.repill.was.market.entity;

public interface MarketReviewCommentRepository {
    MarketReviewCommentId nextId();

    MarketReviewComment save(MarketReviewComment marketReview);
}
