package com.repill.was.festival.repository;

import com.repill.was.festival.entity.Festival;
import com.repill.was.festival.entity.FestivalId;
import com.repill.was.festival.entity.FestivalRepository;
import com.repill.was.festival.repository.jpa.FestivalJpaRepository;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.market.entity.Market;
import com.repill.was.market.entity.MarketId;
import com.repill.was.market.entity.MarketRepository;
import com.repill.was.market.repository.jpa.MarketJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FestivalRepositoryImpl implements FestivalRepository {

    private final SequenceGenerator sequenceGenerator;
    private final FestivalJpaRepository festivalJpaRepository;

    @Override
    public FestivalId nextId() {
        return new FestivalId(sequenceGenerator.generate());
    }

    @Override
    public Festival save(Festival festival) {
        return festivalJpaRepository.save(festival);
    }
}
