package com.repill.was.member.repository.jpa;

import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecentlyViewedItemJpaRepository extends JpaRepository<RecentlyViewedItem, RecentlyViewedItemId> {

    Optional<RecentlyViewedItem> findByIdAndMemberId(RecentlyViewedItemId id, MemberId memberId);
}
