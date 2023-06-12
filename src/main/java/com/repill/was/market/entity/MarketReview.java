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
public class MarketReview {

    @EmbeddedId
    MarketReviewId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "marketId", nullable = false))
    MarketId marketId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId", nullable = false))
    MemberId memberId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String imageSrc;

    @Column(columnDefinition = "DATETIME(3)")
    private ZonedDateTime visitedAt;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Column(columnDefinition = "TINYINT DEFAULT 1", nullable = false)
    private int score;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private ZonedDateTime createdAt;

    @Column(columnDefinition = "DATETIME(3)")
    private ZonedDateTime updatedAt;
}
