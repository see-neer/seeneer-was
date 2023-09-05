package com.repill.was.member.repository.jpa;

import com.repill.was.member.entity.favoriteitems.FavoriteItem;
import com.repill.was.member.entity.favoriteitems.FavoriteItemId;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemId;
import com.repill.was.member.repository.FavoriteItemImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteItemJpaRepository extends JpaRepository<FavoriteItem, FavoriteItemId> {

    Optional<FavoriteItem> findByIdAndMemberId(FavoriteItemId id, MemberId memberId);
}
