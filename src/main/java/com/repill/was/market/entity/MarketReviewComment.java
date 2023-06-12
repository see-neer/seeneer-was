package com.repill.was.market.entity;

import com.repill.was.member.entity.MemberId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MarketReviewComment {

    public enum Emoji {
        HELPFUL, THANK, GOOD, AMAZING
    }

    @EmbeddedId
    MarketReviewCommentId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "marketReviewId", nullable = false))
    MarketReviewId marketReviewId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "postMemberId", nullable = false))
    MemberId postMemberId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "commentMemberId", nullable = false))
    MemberId commentMemberId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Column(columnDefinition = "VARCHAR(85)", nullable = false)
    private Emoji emoji;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private ZonedDateTime createdAt;

    @Column(columnDefinition = "DATETIME(3)")
    private ZonedDateTime updatedAt;
}
