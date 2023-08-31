package com.repill.was.comment.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@DiscriminatorValue("MARKET")
public class MarketReviewComment extends Comment {

    @EmbeddedId
    MarketReviewCommentId id;

    public MarketReviewComment(MarketReviewCommentId id) {
        this.id = id;
    }
}
