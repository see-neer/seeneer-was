package com.repill.was.member.entity;

import com.repill.was.market.entity.MarketId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "UK_MEMBER_ID_AND_MARKET_ID", columnList = "memberId, marketId", unique = true)
})
public class MemberFavoriteMarket {

    @EmbeddedId
    MemberFavoriteMarketId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId", nullable = false))
    MemberId memberId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "marketId", nullable = false))
    MarketId marketId;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private ZonedDateTime createdAt;
}
