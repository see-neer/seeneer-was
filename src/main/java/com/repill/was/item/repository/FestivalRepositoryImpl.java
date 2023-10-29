package com.repill.was.item.repository;




import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.item.controller.dto.response.FestivalInfoResponse;
import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.entity.FestivalRepository;
import com.repill.was.item.entity.QFestival;
import com.repill.was.item.repository.jpa.FestivalJpaRepository;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.operation.entity.QAddressInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.repill.was.item.entity.QFestival.*;
import static com.repill.was.operation.entity.QAddressInfo.addressInfo;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class FestivalRepositoryImpl implements FestivalRepository {

    private final SequenceGenerator sequenceGenerator;
    private final FestivalJpaRepository festivalJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public FestivalId nextId() {
        return new FestivalId(sequenceGenerator.generate());
    }

    @Override
    public Festival save(Festival festival) {
        return festivalJpaRepository.save(festival);
    }

    @Override
    public Optional<Festival> findById(FestivalId festivalId) {
        return festivalJpaRepository.findById(festivalId);
    }

    @Override
    public List<Festival> getLists(String addressDetailA, String addressDetailB, LocalDateTime date) {
        return jpaQueryFactory
                .select(festival)
                .from(festival)
                .innerJoin(addressInfo).on(festival.addressDetailA.eq(addressInfo.addressDetailA).and(festival.addressDetailB.eq(addressInfo.addressDetailB)))
                .where(cursorEq(date))
                .fetch();
    }

    @Override
    public Festival getDetail(FestivalId festivalId, String addressDetailA, String addressDetailB, LocalDateTime date) {
        return jpaQueryFactory
                .select(festival)
                .from(festival)
                .innerJoin(addressInfo).on(festival.addressDetailA.eq(addressInfo.addressDetailA).and(festival.addressDetailB.eq(addressInfo.addressDetailB)))
                .where(cursorEq(date))
                .fetchOne();
    }

    private BooleanExpression cursorEq(LocalDateTime date) {
        return isEmpty(date) ? null : festival.date.eq(date);
    }
}
