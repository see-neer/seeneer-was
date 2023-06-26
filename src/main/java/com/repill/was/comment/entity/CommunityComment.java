package com.repill.was.comment.entity;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("COMMUNITY")
public class CommunityComment {

    @EmbeddedId
    MarketReviewCommentId id;
}
