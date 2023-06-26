package com.repill.was.market.repository;

import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.market.entity.*;
import com.repill.was.market.repository.jpa.MarketReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MarketReviewRepositoryImpl implements MarketReviewRepository {

    private final SequenceGenerator sequenceGenerator;
    private final MarketReviewJpaRepository marketReviewJpaRepository;

    @Override
    public MarketReviewId nextId() {
        return new MarketReviewId(sequenceGenerator.generate());
    }

    @Override
    public MarketReview save(MarketReview address) {
        return marketReviewJpaRepository.save(address);
    }
}
