package com.repill.was.operation.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.global.enums.ItemType;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.entity.MarketId;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.operation.entity.AddressInfo;
import com.repill.was.operation.entity.AddressInfoRepository;
import com.repill.was.review.entity.Review;
import com.repill.was.review.entity.ReviewId;
import com.repill.was.review.entity.ReviewRepository;
import com.repill.was.review.query.vo.QReviewDetailVO;
import com.repill.was.review.query.vo.QReviewVO;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;
import com.repill.was.review.repository.jpa.ReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.repill.was.item.entity.QFestival.festival;
import static com.repill.was.item.entity.QMarket.market;
import static com.repill.was.review.entity.QReview.review;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class AddressInfoRepositoryImpl implements AddressInfoRepository {
    private final AddressInfoJpaRepository addressInfoJpaRepository;

    @Override
    public List<AddressInfo> findAll() {
        return addressInfoJpaRepository.findAll();
    }

    @Override
    public Optional<AddressInfo> findByAddressDetailA(String addressDetailA) {
        return addressInfoJpaRepository.findByAddressDetailA(addressDetailA);
    }

    @Override
    public Optional<AddressInfo> findByAddressDetailB(String addressDetailB) {
        return addressInfoJpaRepository.findByAddressDetailB(addressDetailB);
    }

    @Override
    public Optional<AddressInfo> findByAddressDetailAAndAddressDetailB(String addressDetailA, String addressDetailB) {
        return addressInfoJpaRepository.findByAddressDetailAAndAddressDetailB(addressDetailA, addressDetailB);
    }
}
