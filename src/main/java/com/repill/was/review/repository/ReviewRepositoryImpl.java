package com.repill.was.review.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.global.enums.ItemType;
import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.entity.MarketId;
import com.repill.was.item.entity.QFestival;
import com.repill.was.item.entity.QMarket;
import com.repill.was.member.entity.member.MemberId;
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
public class ReviewRepositoryImpl implements ReviewRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final SequenceGenerator sequenceGenerator;
    private final ReviewJpaRepository reviewJpaRepository;


    @Override
    public ReviewId nextId() {
        return new ReviewId(sequenceGenerator.generate());
    }

    @Override
    public Review save(Review review) {
        return reviewJpaRepository.save(review);
    }

    @Override
    public void delete(Review review) {
        reviewJpaRepository.delete(review);
    }

    @Override
    public Optional<Review> findById(ReviewId reviewId) {
        return reviewJpaRepository.findById(reviewId);
    }

    @Override
    public List<ReviewVO> getMarketReviewLists(MemberId memberId, MarketId cursorId, int size) {
        return jpaQueryFactory
                .select(new QReviewVO(
                        review.id.id,
                        market.id.id,
                        market.images,
                        review.createdAt,
                        review.content))
                .from(review)
                .leftJoin(market).on(review.itemId.eq(market.id.id))
                .where(review.reviewerId.eq(memberId)
                        .and(review.itemType.eq(ItemType.MARKET)
                                .and(cursorLt(cursorId.getId()))))
                .fetch();
    }

    @Override
    public List<ReviewVO> getFestivalReviewLists(MemberId memberId, FestivalId cursorId, int size) {
        return jpaQueryFactory
                .select(new QReviewVO(
                        review.id.id,
                        festival.id.id,
                        festival.images,
                        review.createdAt,
                        review.content))
                .from(review)
                .leftJoin(festival).on(review.itemId.eq(festival.id.id))
                .where(review.reviewerId.eq(memberId)
                        .and(review.itemType.eq(ItemType.FESTIVAL)
                                .and(cursorLt(cursorId.getId()))))
                .fetch();
    }

    @Override
    public ReviewDetailVO getMarketReviewDetail(ReviewId reviewId, MarketId marketId) {
        return jpaQueryFactory
                .select(new QReviewDetailVO(
                        market.id.id,
                        market.images,
                        review.createdAt,
                        review.content,
                        market.name
                ))
                .from(market)
                .leftJoin(review).on(review.itemId.eq(market.id.id))
                .where(review.id.eq(reviewId))
                .fetchOne();
    }

    @Override
    public ReviewDetailVO getFestivalReviewDetail(ReviewId reviewId, FestivalId festivalId) {
        return jpaQueryFactory
                .select(new QReviewDetailVO(
                        festival.id.id,
                        festival.images,
                        review.createdAt,
                        review.content,
                        festival.name
                ))
                .from(festival)
                .leftJoin(review).on(review.itemId.eq(festival.id.id))
                .where(review.id.eq(reviewId))
                .fetchOne();
    }

    private BooleanExpression cursorLt(Long cursor) {
        return isEmpty(cursor) ? null : review.id.id.lt(cursor);
    }
}
