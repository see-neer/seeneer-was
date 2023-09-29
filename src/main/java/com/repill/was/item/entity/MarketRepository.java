package com.repill.was.item.entity;

import java.util.Optional;

public interface MarketRepository {
    MarketId nextId();

    Market save(Market market);

    Optional<Market> findById(MarketId marketId);
}
