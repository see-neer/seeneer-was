package com.repill.was.global.factory.itemvalidate;

import com.repill.was.global.enums.ItemType;
import com.repill.was.item.entity.*;
import com.repill.was.item.query.FestivalQueries;
import com.repill.was.item.query.vo.ItemVO;
import com.repill.was.member.entity.member.*;
import com.repill.was.review.query.ReviewQueries;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class FestivalItemValidator implements ItemValidator {
    private final ReviewQueries reviewQueries;
    private final FestivalQueries festivalQueries;
    private final MemberRepository memberRepository;

    @Override
    public ItemType getSupportType() {
        return ItemType.FESTIVAL;
    }

    @Override
    public List<ReviewVO> getReviewList(MemberId memberId, Long cursorId, int size) {
        return reviewQueries.getFestivalReviewLists(memberId, cursorId, size);
    }

    @Override
    public ItemVO getItemInfo(Long itemId) {
        Festival festival = festivalQueries.findById(new FestivalId(itemId)).orElseThrow(FestivalNotFoundException::new);
        return ItemVO.from(festival);
    }

    @Override
    public ReviewDetailVO getReviewDetailList(Long id, Long itemId) {
        return reviewQueries.getFestivalReviewDetail(id, itemId);
    }

    @Override
    public void addRecentlyViewedItem(Member member, Long itemId) {
        member.addRecentlyViewedItems(RecentlyViewedItem.newOne(ItemType.FESTIVAL, itemId));
        memberRepository.save(member);
    }

    @Override
    public void addFavoriteItem(Member member, Long itemId) {
        member.addFavoriteItem(FavoriteItem.newOne(ItemType.FESTIVAL, itemId));
        memberRepository.save(member);
    }

    @Override
    public void deleteFavoriteItem(Member member, Long itemId) {
        member.deleteFavoriteItem(FavoriteItem.newOne(ItemType.FESTIVAL, itemId));
        memberRepository.save(member);
    }

    @Override
    public void deleteRecentlyViewedItem(Member member, Long itemId) {
        member.deleteRecentlyViewedItem(RecentlyViewedItem.newOne(ItemType.FESTIVAL, itemId));
        memberRepository.save(member);
    }
}
