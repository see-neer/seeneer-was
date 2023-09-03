package com.repill.was.operation.controller;

import com.repill.was.festival.entity.Festival;
import com.repill.was.festival.entity.FestivalRepository;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.market.entity.Market;
import com.repill.was.market.entity.MarketRepository;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberRepository;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItem;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemRepository;
import com.repill.was.member.query.MemberQueries;
import com.repill.was.member.repository.jpa.RecentlyViewedItemJpaRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@MockV1Controller
@RequiredArgsConstructor
public class MockController {

    private final MarketRepository marketRepository;
    private final FestivalRepository festivalRepository;
    private final RecentlyViewedItemRepository recentlyViewedItemRepository;
    private final MemberRepository memberRepository;

    @ApiOperation("market 추가")
    @PostMapping("/mock-add-market")
    public void addMockMarket() {
        marketRepository.save(Market.newOne(
                marketRepository.nextId(),
                "https://repill-dev.s3.ap-northeast-2.amazonaws.com/test/KakaoTalk_Photo_2023-07-30-18-44-36.jpeg",
                "Mock Market",
                "Mock Market Address",
                "Mock Market Date"
        ));
    }

    @ApiOperation("festival 추가")
    @PostMapping("/mock-add-festival")
    public void addMockFestival() {
        festivalRepository.save(Festival.newOne(
                festivalRepository.nextId(),
                "https://repill-dev.s3.ap-northeast-2.amazonaws.com/test/KakaoTalk_Photo_2023-07-30-18-44-36.jpeg",
                "Mock Festival",
                "Mock Festival Festival",
                "Mock Festival Date"
        ));
    }

    @ApiOperation("내가 본 목록 추가")
    @PostMapping("/mock-add-recently-viewed")
    public void addMockRecentlyViewed(@RequestParam Long accountId,
                                      @RequestParam String itemType,
                                      @RequestParam Long itemId) {
        recentlyViewedItemRepository.save(RecentlyViewedItem.newOne(
                recentlyViewedItemRepository.nextId(),
                new MemberId(accountId),
                RecentlyViewedItem.ItemType.valueOf(itemType),
                itemId
        ));
    }

    @ApiOperation("찜 목록 추가")
    @PostMapping("/mock-add-selected")
    public void addMockSelected(@RequestParam Long accountId,
                                @RequestParam String itemType,
                                @RequestParam Long itemId) {
        recentlyViewedItemRepository.save(RecentlyViewedItem.newOne(
                recentlyViewedItemRepository.nextId(),
                new MemberId(accountId),
                RecentlyViewedItem.ItemType.valueOf(itemType),
                itemId
        ));
    }
}
