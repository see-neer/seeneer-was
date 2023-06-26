package com.repill.was.comment.entity;

public interface MarketReviewCommentRepository {
    MarketReviewCommentId nextId();

    MarketReviewComment save(MarketReviewComment marketReview);
}
