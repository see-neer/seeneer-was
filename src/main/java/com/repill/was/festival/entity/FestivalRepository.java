package com.repill.was.festival.entity;

import com.repill.was.market.entity.Market;
import com.repill.was.market.entity.MarketId;

import java.util.Optional;

public interface FestivalRepository {
    FestivalId nextId();

    Festival save(Festival market);

    Optional<Festival> findById(FestivalId festivalId);
}
