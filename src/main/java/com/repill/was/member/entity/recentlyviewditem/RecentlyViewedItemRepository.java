package com.repill.was.member.entity.recentlyviewditem;

import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;

import java.util.Optional;

public interface RecentlyViewedItemRepository {

    RecentlyViewedItemId nextId();

    RecentlyViewedItem save(RecentlyViewedItem member);
}
