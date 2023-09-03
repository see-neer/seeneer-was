package com.repill.was.festival.repository.jpa;

import com.repill.was.festival.entity.Festival;
import com.repill.was.festival.entity.FestivalId;
import com.repill.was.market.entity.Market;
import com.repill.was.market.entity.MarketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalJpaRepository extends JpaRepository<Festival, FestivalId> {
}
