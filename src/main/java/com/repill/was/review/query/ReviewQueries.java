package com.repill.was.review.query;

import com.repill.was.festival.entity.FestivalId;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.shard.enums.ItemType;
import com.repill.was.market.entity.MarketId;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.review.entity.ReviewId;
import com.repill.was.review.entity.ReviewRepository;
import com.repill.was.review.query.vo.ReviewDetailVO;
import com.repill.was.review.query.vo.ReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueries {

    private final ReviewRepository reviewRepository;

    public List<ReviewVO> getList(MemberId memberId, ItemType itemType, Long cursorId, int size) {
        if(ItemType.MARKET.equals(itemType)) return reviewRepository.getMarketList(memberId, cursorId, size);
        if(ItemType.FESTIVAL.equals(itemType)) return reviewRepository.getFestivalList(memberId, cursorId, size);
        throw new BadRequestException("존재하지 않는 아이템 타입");
    }

    public ReviewDetailVO getReviewDetail(Long id, Long itemId, ItemType itemType) {
        if(ItemType.MARKET.equals(itemType)) return reviewRepository.getMarketReviewDetail(new ReviewId(id), new MarketId(itemId));
        if(ItemType.FESTIVAL.equals(itemType)) return reviewRepository.getFestivalReviewDetail(new ReviewId(id), new FestivalId(itemId));
        throw new BadRequestException("존재하지 않는 아이템 타입");
    }
}
