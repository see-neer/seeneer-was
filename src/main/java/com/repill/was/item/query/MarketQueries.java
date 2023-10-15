package com.repill.was.item.query;

import com.repill.was.item.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarketQueries {

    private final MarketRepository marketRepository;

    public Optional<Market> findById(MarketId marketId) {
        return marketRepository.findById(marketId);
    }
}
