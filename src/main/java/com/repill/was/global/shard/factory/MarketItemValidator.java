package com.repill.was.global.shard.factory;

import com.repill.was.global.shard.enums.ItemType;
import com.repill.was.member.entity.member.MemberId;
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

    @Override
    public ItemType getSupportType() {
        return ItemType.MARKET;
    }

    @Override
    public List<ReviewVO> getReviewList(MemberId memberId, Long cursorId, int size) {
        return reviewQueries.getMarketReviewLists(memberId, cursorId, size);
    }

    @Override
    public ReviewDetailVO getReviewDetailList(Long id, Long itemId) {
        return reviewQueries.getMarketReviewDetail(id, itemId);
    }
}
