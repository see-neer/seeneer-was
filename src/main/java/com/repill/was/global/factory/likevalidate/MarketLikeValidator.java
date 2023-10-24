package com.repill.was.global.factory.likevalidate;

import com.repill.was.global.enums.ItemType;
import com.repill.was.global.factory.itemvalidate.ItemValidator;
import com.repill.was.item.entity.Market;
import com.repill.was.item.entity.MarketId;
import com.repill.was.item.entity.MarketNotFoundException;
import com.repill.was.item.entity.MarketRepository;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLike.LikeType;
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
public class MarketLikeValidator implements LikeValidator {
    private final MarketRepository marketRepository;

    @Override
    public LikeType getSupportType() {
        return LikeType.MARKET;
    }

    @Override
    public void addLike(Long itemId, MemberLike newMemberLike) {
        Market market = marketRepository.findById(new MarketId(itemId)).orElseThrow(MarketNotFoundException::new);
        market.addLike(newMemberLike);
        marketRepository.save(market);
    }

    @Override
    public void unLike(Long itemId, MemberLike deleteMemberLike) {
        Market market = marketRepository.findById(new MarketId(itemId)).orElseThrow(MarketNotFoundException::new);
        market.unLike(deleteMemberLike);
        marketRepository.save(market);
    }
}
