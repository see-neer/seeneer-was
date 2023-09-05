package com.repill.was.member.entity.favoriteitems;

import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemId;
import com.repill.was.member.repository.vo.RecentlyViewedItemInfoVO;

import java.util.List;
import java.util.Optional;

public interface FavoriteItemRepository {

    FavoriteItemId nextId();

    void save(FavoriteItem member);

    List<RecentlyViewedItemInfoVO> findByMemberId(MemberId memberId, int size, Long cursorId);

    Optional<FavoriteItem> findByIdAndAccountId(FavoriteItemId id, MemberId memberId);

    void delete(FavoriteItem recentlyViewedItem);
}
