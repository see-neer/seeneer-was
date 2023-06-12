package com.repill.was.market.entity;

public interface MarketRepository {
    MarketId nextId();

    Market save(Market market);
}
