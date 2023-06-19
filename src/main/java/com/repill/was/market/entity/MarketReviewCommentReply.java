package com.repill.was.market.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MarketReviewCommentReply {

    @EmbeddedId
    MarketReviewCommentReplyId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "marketReviewCommentId", nullable = false))
    MarketReviewCommentId marketReviewCommentId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private ZonedDateTime createdAt;

    @Column(columnDefinition = "DATETIME(3)")
    private ZonedDateTime updatedAt;
}
