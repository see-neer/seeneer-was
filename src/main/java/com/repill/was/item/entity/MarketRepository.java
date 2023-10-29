package com.repill.was.item.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MarketRepository {
    MarketId nextId();

    Market save(Market market);

    Optional<Market> findById(MarketId marketId);

    List<Market> getLists(String addressDetailA, String addressDetailB, String date);

    Market getDetail(MarketId festivalId, String addressDetailA, String addressDetailB, String date);
}
