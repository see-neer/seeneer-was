package com.repill.was.market.entity;

import com.repill.was.festival.entity.Festival;
import com.repill.was.festival.entity.FestivalId;

public interface MarketRepository {
    MarketId nextId();

    Market save(Market market);
}
