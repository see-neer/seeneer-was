package com.repill.was.review.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.festival.entity.FestivalId;
import com.repill.was.festival.entity.QFestival;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.global.shard.enums.ItemType;
import com.repill.was.market.entity.MarketId;
import com.repill.was.market.entity.QMarket;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.review.entity.QReview;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.repill.was.festival.entity.QFestival.festival;
import static com.repill.was.market.entity.QMarket.market;
import static com.repill.was.member.entity.favoriteitems.QFavoriteItem.favoriteItem;
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
    public List<ReviewVO> getMarketList(MemberId memberId, Long cursorId, int size) {
        return jpaQueryFactory
                .select(new QReviewVO(
                        review.id.id,
                        market.id.id,
                        market.imageSrc,
                        review.createdAt,
                        review.content))
                .from(review)
                .leftJoin(market).on(review.itemId.eq(market.id.id))
                .where(review.memberId.eq(memberId)
                        .and(review.itemType.eq(ItemType.MARKET)
                                .and(cursorLt(cursorId))))
                .fetch();
    }

    @Override
    public List<ReviewVO> getFestivalList(MemberId memberId, Long cursorId, int size) {
        return jpaQueryFactory
                .select(new QReviewVO(
                        review.id.id,
                        festival.id.id,
                        festival.imageSrc,
                        review.createdAt,
                        review.content))
                .from(review)
                .leftJoin(festival).on(review.itemId.eq(festival.id.id))
                .where(review.memberId.eq(memberId)
                        .and(review.itemType.eq(ItemType.FESTIVAL)
                                .and(cursorLt(cursorId))))
                .fetch();
    }

    @Override
    public ReviewDetailVO getMarketReviewDetail(ReviewId reviewId, MarketId marketId) {
        return jpaQueryFactory
                .select(new QReviewDetailVO(
                        market.id.id,
                        market.imageSrc,
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
                        festival.imageSrc,
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
