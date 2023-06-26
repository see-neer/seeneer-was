package com.repill.was.community.entity;

import com.repill.was.market.entity.Market;
import com.repill.was.market.entity.MarketId;

public interface CommunityRepository {
    CommunityId nextId();

    Community save(Community community);
}
