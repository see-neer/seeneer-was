package com.repill.was.member.entity.recentlyviewditem;

import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor
public class RecentlyViewedItem {

    public enum ItemType {
        MARKET, FESTIVAL
    }

    @EmbeddedId
    private RecentlyViewedItemId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "memberId", nullable = false))
    MemberId memberId;

    @Column(columnDefinition = "VARCHAR(50)")
    private ItemType itemType;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long itemId;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private ZonedDateTime createdAt;
}
