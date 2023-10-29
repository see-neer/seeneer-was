package com.repill.was.item.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.item.entity.*;
import com.repill.was.item.repository.jpa.MarketJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.repill.was.item.entity.QFestival.festival;
import static com.repill.was.item.entity.QMarket.market;
import static com.repill.was.operation.entity.QAddressInfo.addressInfo;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class MarketRepositoryImpl implements MarketRepository {

    private final SequenceGenerator sequenceGenerator;
    private final MarketJpaRepository marketJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

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

    @Override
    public List<Market> getLists(String addressDetailA, String addressDetailB, String date) {
        return jpaQueryFactory
                .select(market)
                .from(market)
                .innerJoin(addressInfo).on(market.addressDetailA.eq(addressInfo.addressDetailA).and(market.addressDetailB.eq(addressInfo.addressDetailB)))
                .where(cursorEq(date))
                .fetch();
    }

    @Override
    public Market getDetail(MarketId festivalId, String addressDetailA, String addressDetailB, String date) {
        return jpaQueryFactory
                .select(market)
                .from(market)
                .innerJoin(addressInfo).on(market.addressDetailA.eq(addressInfo.addressDetailA).and(market.addressDetailB.eq(addressInfo.addressDetailB)))
                .where(market.id.eq(festivalId))
                .where(cursorEq(date))
                .fetchOne();
    }

    private BooleanExpression cursorEq(String date) {
        return isEmpty(date) ? null : market.date.eq(date);
    }
}
