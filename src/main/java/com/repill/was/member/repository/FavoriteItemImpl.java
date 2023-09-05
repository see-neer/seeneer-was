package com.repill.was.member.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.festival.entity.QFestival;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.favoriteitems.FavoriteItem;
import com.repill.was.member.entity.favoriteitems.FavoriteItemRepository;
import com.repill.was.member.entity.favoriteitems.FavoriteItemId;
import com.repill.was.member.entity.favoriteitems.QFavoriteItem;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemRepository;
import com.repill.was.member.repository.jpa.FavoriteItemJpaRepository;
import com.repill.was.member.repository.jpa.RecentlyViewedItemJpaRepository;
import com.repill.was.member.repository.vo.QRecentlyViewedItemInfoVO;
import com.repill.was.member.repository.vo.RecentlyViewedItemInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.repill.was.festival.entity.QFestival.festival;
import static com.repill.was.member.entity.favoriteitems.QFavoriteItem.favoriteItem;
import static com.repill.was.member.entity.recentlyviewditem.QRecentlyViewedItem.recentlyViewedItem;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class FavoriteItemImpl implements FavoriteItemRepository {

    private final SequenceGenerator sequenceGenerator;
    private final FavoriteItemJpaRepository favoriteItemJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public FavoriteItemId nextId() {
        return new FavoriteItemId(sequenceGenerator.generate());
    }

    @Override
    public void save(FavoriteItem favoriteItem) {
        favoriteItemJpaRepository.save(favoriteItem);
    }

    @Override
    public List<RecentlyViewedItemInfoVO> findByMemberId(MemberId memberId, int size, Long cursorId) {
        return jpaQueryFactory
                .select(new QRecentlyViewedItemInfoVO(
                        favoriteItem.id.id,
                        favoriteItem.itemId,
                        favoriteItem.itemType
                ))
                .from(favoriteItem)
                .where(favoriteItem.memberId.eq(memberId)
                        .and(cursorLt(cursorId)))
                .offset(size)
                .fetch();
    }

    @Override
    public Optional<FavoriteItem> findByIdAndAccountId(FavoriteItemId id, MemberId memberId) {
        return Optional.empty();
    }

    @Override
    public void delete(FavoriteItem recentlyViewedItem) {

    }

    private BooleanExpression cursorLt(Long cursor) {
        return isEmpty(cursor) ? null : favoriteItem.id.id.lt(cursor);
    }
}
