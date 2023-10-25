package com.repill.was.global.factory.itemvalidate;

import com.repill.was.global.enums.ItemType;
import com.repill.was.item.entity.Market;
import com.repill.was.item.query.vo.ItemVO;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;

import java.util.List;
import java.util.Optional;

public interface ItemValidator {
    ItemType getSupportType();
    List<ReviewVO> getReviewList(MemberId memberId);

    ItemVO getItemInfo(Long itemId);

    ReviewDetailVO getReviewDetailList(Long id, Long itemId);

    void addRecentlyViewedItem(Member member, Long itemId);

    void addFavoriteItem(Member member, Long itemId);

    void deleteFavoriteItem(Member member, Long itemId);

    void deleteRecentlyViewedItem(Member member, Long itemId);

}
