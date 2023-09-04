package com.repill.was.member.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.market.entity.MarketRepository;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.recentlyviewditem.QRecentlyViewedItem;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemRepository;
import com.repill.was.member.repository.jpa.RecentlyViewedItemJpaRepository;
import com.repill.was.member.repository.vo.QRecentlyViewedItemInfoVO;
import com.repill.was.member.repository.vo.RecentlyViewedItemInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.repill.was.member.entity.recentlyviewditem.QRecentlyViewedItem.recentlyViewedItem;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class RecentlyViewedItemImpl implements RecentlyViewedItemRepository {

    private final SequenceGenerator sequenceGenerator;
    private final RecentlyViewedItemJpaRepository recentlyViewedItemJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public RecentlyViewedItemId nextId() {
        return new RecentlyViewedItemId(sequenceGenerator.generate());
    }

    @Override
    public RecentlyViewedItem save(RecentlyViewedItem recentlyViewedItem) {
        return recentlyViewedItemJpaRepository.save(recentlyViewedItem);
    }

    @Override
    public List<RecentlyViewedItemInfoVO> findByMemberId(MemberId memberId, int size, Long cursorId) {
        return jpaQueryFactory
                .select(new QRecentlyViewedItemInfoVO(
                        recentlyViewedItem.id.id,
                        recentlyViewedItem.itemId,
                        recentlyViewedItem.itemType
                ))
                .from(recentlyViewedItem)
                .where(recentlyViewedItem.memberId.eq(memberId)
                        .and(cursorLt(cursorId)))
                .offset(size)
                .fetch();
    }

    private BooleanExpression cursorLt(Long cursor) {
        return isEmpty(cursor) ? null : recentlyViewedItem.id.id.lt(cursor);
    }
}
