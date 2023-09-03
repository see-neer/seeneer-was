package com.repill.was.member.repository;

import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemRepository;
import com.repill.was.member.repository.jpa.RecentlyViewedItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecentlyViewedItemImpl implements RecentlyViewedItemRepository {

    private final SequenceGenerator sequenceGenerator;
    private final RecentlyViewedItemJpaRepository recentlyViewedItemJpaRepository;

    @Override
    public RecentlyViewedItemId nextId() {
        return new RecentlyViewedItemId(sequenceGenerator.generate());
    }

    @Override
    public RecentlyViewedItem save(RecentlyViewedItem recentlyViewedItem) {
        return recentlyViewedItemJpaRepository.save(recentlyViewedItem);
    }
}
