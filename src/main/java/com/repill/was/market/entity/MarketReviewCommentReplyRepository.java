package com.repill.was.market.entity;

public interface MarketReviewCommentReplyRepository {
    MarketReviewCommentReplyId nextId();

    MarketReviewCommentReply save(MarketReviewCommentReply marketReviewCommentReply);
}
