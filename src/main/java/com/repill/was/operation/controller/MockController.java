package com.repill.was.operation.controller;

import com.repill.was.global.config.JwtTokenProvider;
import com.repill.was.global.enums.OSType;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.enums.ItemType;
import com.repill.was.global.model.ImageListData;
import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.FestivalRepository;
import com.repill.was.item.entity.Market;
import com.repill.was.item.entity.MarketRepository;
import com.repill.was.member.controller.command.MemberLikeCommand;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountRepository;

import com.repill.was.member.entity.account.Device;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberRepository;

import com.repill.was.member.query.MemberQueries;
import com.repill.was.member.service.MemberLikeService;
import com.repill.was.review.entity.Review;
import com.repill.was.review.entity.ReviewRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@MockV1Controller
@RequiredArgsConstructor
public class MockController {

    private final MarketRepository marketRepository;
    private final FestivalRepository festivalRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountRepository accountRepository;
    private final MemberQueries memberQueries;

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberLikeService memberLikeService;

    @ApiOperation("market 추가")
    @PostMapping("/mock-add-market")
    public void addMockMarket() {
        marketRepository.save(Market.newOne(
                marketRepository.nextId(),
                ImageListData.from(List.of("https://repill-dev.s3.ap-northeast-2.amazonaws.com/test/KakaoTalk_Photo_2023-07-30-18-44-36.jpeg")),
                "Mock Market",
                "Mock Market Address",
                "Mock Market Date"
        ));
    }

    @ApiOperation("Mock 좋아요 실헹")
    @PostMapping("/mock-add-like-item")
    public void addMockLikeItem(
    ) throws InterruptedException {
        memberLikeService.addLike(
                MemberLikeCommand.request(
                        new MemberId(616267619704864L),
                        "FESTIVAL",
                        616268181700640L
                )
        );
    }


    @ApiOperation("festival 추가")
    @PostMapping("/mock-add-festival")
    public void addMockFestival() {
        festivalRepository.save(Festival.newOne(
                festivalRepository.nextId(),
                ImageListData.from(List.of("https://repill-dev.s3.ap-northeast-2.amazonaws.com/test/KakaoTalk_Photo_2023-07-30-18-44-36.jpeg")),
                "Mock Festival",
                "Mock Festival Festival",
                LocalDateTime.now()
        ));
    }

//    @ApiOperation("내가 본 목록 추가")
//    @PostMapping("/mock-add-recently-viewed")
//    public void addMockRecentlyViewed(@RequestParam Long accountId,
//                                      @RequestParam String itemType,
//                                      @RequestParam Long itemId) {
//        recentlyViewedItemRepository.save(RecentlyViewedItem.newOne(
//                recentlyViewedItemRepository.nextId(),
//                new MemberId(accountId),
//                ItemType.valueOf(itemType),
//                itemId
//        ));
//    }

    @ApiOperation("찜 목록 추가")
    @PostMapping("/mock-add-favorite-item")
    public void addMockSelected(@RequestParam Long accountId,
                                @RequestParam String itemType,
                                @RequestParam Long itemId) {
//        Member byAccountId = memberQueries.findByAccountId(new AccountId(accountId)).get();
//
//
//        favoriteItemRepository.save(FavoriteItem.newOne(
//                favoriteItemRepository.nextId(),
//                new MemberId(accountId),
//                ItemType.valueOf(itemType),
//                itemId
//        ));
    }

    @ApiOperation("리뷰 목록 추가")
    @PostMapping("/mock-add-review")
    public void addMockReview(@RequestParam Long memberId,
                                @RequestParam String itemType,
                                @RequestParam Long itemId) {
        Review mock_content = Review.from(
                reviewRepository.nextId(),
                new MemberId(memberId),
                ItemType.valueOf(itemType),
                itemId,
                "MOCK CONTENT",
                ImageListData.from(List.of()));
        reviewRepository.save(mock_content);
    }

    @ApiOperation("카카오 로그인 초기화")
    @DeleteMapping("/mock-delete-user")
    public void addMockSelected(@AuthenticationPrincipal AccountId accountId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(BadRequestException::new);
        memberRepository.delete(member);
        Account account = accountRepository.findById(member.getAccountId()).orElseThrow(BadRequestException::new);
        accountRepository.delete(account);
//        deviceRepository.clearTokenByAccountId(accountId);
    }

    @ApiOperation("테스트용 계정 생성")
    @PostMapping("/mock-uesr")
    public void addMockUser() {
        AccountId accountId = accountRepository.nextId();
        String token = jwtTokenProvider.createToken(accountId.toString(), "TEST");
        Device device = Device.newOne(
                token,
                "TEST-DEVICE",
                OSType.IOS
        );
        Account mockAccount = Account.newOne(accountId, device);
        accountRepository.save(mockAccount);

        Member memberFromKakao = mockAccount.createMemberFromKakao(
                Member.createNotBannedMember(),
                memberRepository.nextId(),
                accountId,
                "https://k.kakaocdn.net/dn/QQw3c/btstwmOWBJV/oxWtafMCdDOE9vmMPcHqok/img_640x640.jpg",
                "TEST계정",
                12345L,
                "TEST",
                "TEST",
                "TEST",
                "TEST",
                "TEST"
        );

        memberRepository.save(memberFromKakao);
    }
}
