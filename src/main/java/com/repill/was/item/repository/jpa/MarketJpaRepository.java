package com.repill.was.item.repository.jpa;

import com.repill.was.item.entity.Market;
import com.repill.was.item.entity.MarketId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketJpaRepository extends JpaRepository<Market, MarketId> {
}
