package com.repill.was.item.query;

import com.repill.was.item.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarketQueries {

    private final MarketRepository marketRepository;

    public Optional<Market> findById(MarketId marketId) {
        return marketRepository.findById(marketId);
    }

    public List<Market> getLists(String addressDetailA, String addressDetailB, String date, int page, int size) {
        return marketRepository.getLists(addressDetailA, addressDetailB, date);
    }

    public Market getDetail(MarketId marketId, String addressDetailA, String addressDetailB, String date) {
        return marketRepository.getDetail(marketId, addressDetailA, addressDetailB, date);
    }
}
