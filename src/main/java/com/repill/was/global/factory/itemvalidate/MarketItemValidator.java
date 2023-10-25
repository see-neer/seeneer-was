package com.repill.was.global.factory.itemvalidate;

import com.repill.was.global.enums.ItemType;
import com.repill.was.item.entity.Market;
import com.repill.was.item.entity.MarketId;
import com.repill.was.item.entity.MarketNotFoundException;
import com.repill.was.item.entity.MarketRepository;
import com.repill.was.item.query.MarketQueries;
import com.repill.was.item.query.vo.ItemVO;
import com.repill.was.member.entity.member.*;
import com.repill.was.review.query.ReviewQueries;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MarketItemValidator implements ItemValidator {

    private final ReviewQueries reviewQueries;
    private final MemberRepository memberRepository;
    private final MarketQueries marketQueries;

    @Override
    public ItemType getSupportType() {
        return ItemType.MARKET;
    }

    @Override
    public List<ReviewVO> getReviewList(MemberId memberId) {
        return reviewQueries.getMarketReviewLists(memberId);
    }

    @Override
    public ItemVO getItemInfo(Long itemId) {
        Market market = marketQueries.findById(new MarketId(itemId)).orElseThrow(MarketNotFoundException::new);
        return ItemVO.from(market);
    }

    @Override
    public ReviewDetailVO getReviewDetailList(Long id, Long itemId) {
        return reviewQueries.getMarketReviewDetail(id, itemId);
    }

    @Override
    public void addRecentlyViewedItem(Member member, Long itemId) {
        member.addRecentlyViewedItems(RecentlyViewedItem.newOne(ItemType.MARKET, itemId));
        memberRepository.save(member);
    }

    @Override
    public void addFavoriteItem(Member member, Long itemId) {
        member.addFavoriteItem(FavoriteItem.newOne(ItemType.MARKET, itemId));
        memberRepository.save(member);
    }

    @Override
    public void deleteFavoriteItem(Member member, Long itemId) {
        member.deleteFavoriteItem(FavoriteItem.newOne(ItemType.MARKET, itemId));
        memberRepository.save(member);
    }

    @Override
    public void deleteRecentlyViewedItem(Member member, Long itemId) {
        member.deleteRecentlyViewedItem(RecentlyViewedItem.newOne(ItemType.MARKET, itemId));
        memberRepository.save(member);
    }
}
