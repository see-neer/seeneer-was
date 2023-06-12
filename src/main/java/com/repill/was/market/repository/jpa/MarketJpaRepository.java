package com.repill.was.market.repository.jpa;

import com.repill.was.market.entity.Market;
import com.repill.was.market.entity.MarketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketJpaRepository extends JpaRepository<Market, MarketId> {
}
