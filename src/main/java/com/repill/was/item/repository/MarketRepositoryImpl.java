package com.repill.was.item.repository;

import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.item.entity.Market;
import com.repill.was.item.entity.MarketId;
import com.repill.was.item.entity.MarketRepository;
import com.repill.was.item.repository.jpa.MarketJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MarketRepositoryImpl implements MarketRepository {

    private final SequenceGenerator sequenceGenerator;
    private final MarketJpaRepository marketJpaRepository;

    @Override
    public MarketId nextId() {
        return new MarketId(sequenceGenerator.generate());
    }

    @Override
    public Market save(Market address) {
        return marketJpaRepository.save(address);
    }

    @Override
    public Optional<Market> findById(MarketId marketId) {
        return marketJpaRepository.findById(marketId);
    }
}
