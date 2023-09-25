package com.repill.was.member.entity.recentlyviewditem;

import com.repill.was.festival.entity.Festival;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.shard.enums.ItemType;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor
public class RecentlyViewedItem {

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
    private LocalDateTime createdAt;

    public RecentlyViewedItem(RecentlyViewedItemId id, MemberId memberId, ItemType itemType, Long itemId, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.itemType = itemType;
        this.itemId = itemId;
        this.createdAt = createdAt;
    }

    public static RecentlyViewedItem newOne(RecentlyViewedItemId id, MemberId memberId, ItemType itemType, Long itemId) {
        return new RecentlyViewedItem(
                id,
                memberId,
                itemType,
                itemId,
                LocalDateTime.now()
        );
    }

    public boolean canDeleteItem(ValidateIsActiveItemService validateIsActiveItemService, Member member) {
        return validateIsActiveItemService.canDeleteItem(member);
    }
}
