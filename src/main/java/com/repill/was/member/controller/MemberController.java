package com.repill.was.member.controller;

import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.shard.enums.Category;
import com.repill.was.global.shard.response.CommonResponse;
import com.repill.was.member.controller.dto.request.*;
import com.repill.was.member.controller.dto.response.MainResponse;
import com.repill.was.member.controller.dto.response.MainResponse.CategoryView;
import com.repill.was.member.controller.dto.response.MemberDetailProfileResponse;
import com.repill.was.member.controller.dto.response.RecentlyViewedItemResponse;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountRepository;
import com.repill.was.member.entity.account.OSType;
import com.repill.was.member.entity.favoriteitems.FavoriteItemId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.recentlyviewditem.RecentlyViewedItemId;
import com.repill.was.member.facade.MemberFacade;
import com.repill.was.member.query.MemberQueries;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import com.repill.was.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.logstash.logback.util.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {SwaggerConfig.SwaggerTags.MEMBER})
@MemberV1Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;
    private final MemberQueries memberQueries;
    private final AccountRepository accountRepository;

    private final MemberService memberService;

    // todo 23.08.31 odo 재경님 같이 작업
    @ApiOperation("카카오 회원가입")
    @PostMapping("/create")
    public CommonResponse<Object> login(@AuthenticationPrincipal AccountId accountId,
                                        @RequestBody MemberLoginRequest memberLoginRequest) {
        memberFacade.login(memberLoginRequest, accountId);
        return CommonResponse.success(null);
    }

    @ApiOperation("메인 화면 호출")
    @GetMapping("/main")
    public MainResponse main(@AuthenticationPrincipal AccountId accountId) {
        List<CategoryView> categoryList = Arrays.stream(Category.values()).map(one -> new CategoryView(one.getDescription(), one.getSubDescription(), one.getIsOpen())).collect(Collectors.toList());
        return MainResponse.from(categoryList);
    }

    @ApiOperation("닉네임 중복 확인")
    @GetMapping("/check-duplicated-nickname")
    public Boolean checkDuplicateNickname(CheckDuplicateNickNameRequest checkDuplicateNickNameRequest) {
        return memberFacade.checkDuplicateNickname(checkDuplicateNickNameRequest.getInsertedNickname());
    }

    @ApiOperation("최근 본 목록 호출")
    @GetMapping("/recently-views")
    public List<RecentlyViewedItemResponse> getRecentlyViewList(@AuthenticationPrincipal AccountId accountId,
                                                                @RequestParam int size,
                                                                @RequestParam(required = false) Long cursorId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(() -> new BadRequestException("존재하지 않는 유저 정보 입니다."));
        return memberFacade.getRecentlyViewList(member.getId(), size, cursorId);
    }

    @ApiOperation("최근 본 목록 삭제하기")
    @DeleteMapping("/recently-view/{id}")
    public void deleteRecentlyView(@AuthenticationPrincipal AccountId accountId,
                                      @PathVariable Long id) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(() -> new BadRequestException("존재하지 않는 유저입니다."));
        memberFacade.deleteRecentlyView(new RecentlyViewedItemId(id), member.getId());
    }

    @ApiOperation("찜 목록 호출")
    @GetMapping("/favorite-items")
    public List<RecentlyViewedItemResponse> getFavoriteItems(@AuthenticationPrincipal AccountId accountId,
                                    @RequestParam int size,
                                    @RequestParam(required = false) Long cursorId) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(() -> new BadRequestException("존재하지 않는 유저 정보 입니다."));
        return memberFacade.getFavoriteItems(member.getId(), size, cursorId);
    }

    @ApiOperation("찜 목록 삭제하기")
    @DeleteMapping("/favorite-item")
    public void deleteFavoriteItem(@AuthenticationPrincipal AccountId accountId,
                                      @PathVariable Long id) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(() -> new BadRequestException("존재하지 않는 유저입니다."));
        memberFacade.deleteFavoriteItem(new FavoriteItemId(id), member.getId());
    }

    @ApiOperation("멤버 로그아웃")
    @PostMapping("/member-logout")
    public void logout(@AuthenticationPrincipal AccountId accountId, @RequestBody MemberLogoutRequest logoutRequest) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            throw new BadRequestException("존재하지 않은 기기 정보 입니다.");
        }
        Optional<Member> member = memberQueries.findByAccountId(accountId);
        if (member.isEmpty()) {
            throw new BadRequestException("존재하지 않은 회원 정보 입니다.");
        }
        memberService.logout(accountId, member.get().getId(), OSType.valueOf(logoutRequest.getOsType()), logoutRequest.getDeviceId());
    }

    @ApiOperation("탈퇴 실행")
    @PostMapping("/close-account")
    public void closeAccount(@AuthenticationPrincipal AccountId accountId, @RequestBody CloseAccountRequest request) {
        if (request.getType().isEmpty() && StringUtils.isEmpty(request.getAdditionalInformation())) {
            throw new BadRequestException();
        }
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(BadRequestException::new);
        memberService.proceedClosingAccount(member, request);
    }

    @ApiOperation("자기 프로필 수정")
    @PutMapping("/my-profiles")
    public void modifyMyProfiles(@AuthenticationPrincipal AccountId accountId,
                                 @RequestBody MemberProfileUpdateRequest request) {

        memberService.update(accountId,
                request.getProfileImageSrc(),
                request.getNickname(),
                request.isHideRealName()
        );
    }

    @ApiOperation("자기 프로필 상세 조회")
    @GetMapping("/my-profile")
    public MemberDetailProfileResponse getMyProfile(@AuthenticationPrincipal AccountId accountId) {
        Optional<Member> member = memberQueries.findByAccountId(accountId);
        if (member.isEmpty()) {
            throw new BadRequestException();
        }
        MemberId memberIdQuery = member.get().getId();
        return memberQueries.findMyProfileByMemberId(memberIdQuery);
    }
}
