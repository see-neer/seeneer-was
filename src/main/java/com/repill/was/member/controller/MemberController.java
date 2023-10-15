package com.repill.was.member.controller;

import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.enums.ItemType;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.response.CommonResponse;
import com.repill.was.member.controller.command.LoginCommand;
import com.repill.was.member.controller.command.MemberAddInformationCommand;
import com.repill.was.member.controller.command.MemberLikeCommand;
import com.repill.was.member.controller.command.MemberNickNameDuplicatedCheckCommand;
import com.repill.was.member.controller.dto.request.*;
import com.repill.was.member.controller.dto.response.MemberDetailProfileResponse;
import com.repill.was.member.controller.dto.response.RecentlyViewedItemResponse;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountNotFoundException;
import com.repill.was.member.entity.account.AccountRepository;

import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberNotFoundException;
import com.repill.was.member.facade.MemberFacade;
import com.repill.was.member.query.MemberLikeQueries;
import com.repill.was.member.query.MemberQueries;

import java.util.List;

import com.repill.was.member.service.MemberLikeService;
import com.repill.was.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.logstash.logback.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {SwaggerConfig.SwaggerTags.MEMBER})
@MemberV1Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;
    private final MemberQueries memberQueries;
    private final AccountRepository accountRepository;
    private final MemberLikeService memberLikeService;
    private final MemberService memberService;
    private final MemberLikeQueries memberLikeQueries;

    @ApiOperation("회원가입")
    @PostMapping("/create")
    public CommonResponse<Object> login(@AuthenticationPrincipal AccountId accountId,
                                        @RequestBody MemberLoginRequest memberLoginRequest) {
        memberFacade.login(LoginCommand.request(memberLoginRequest, accountId));
        return CommonResponse.success(null);
    }

    @ApiOperation("회원 추가정보 업데이트")
    @PostMapping("/information")
    public CommonResponse<Object> addInformation(@AuthenticationPrincipal AccountId accountId,
                                        @RequestBody MemberAddInformationRequest addMemberInformationRequest) {
        memberFacade.addInformation(MemberAddInformationCommand.request(addMemberInformationRequest, accountId));
        return CommonResponse.success(null);
    }

    @ApiOperation("닉네임 중복 확인")
    @GetMapping("/check-duplicated-nickname")
    public Boolean checkDuplicateNickname(@RequestParam String insertedNickname, boolean useKakaoNickname) {
        return memberFacade.checkDuplicateNickname(MemberNickNameDuplicatedCheckCommand.request(insertedNickname, useKakaoNickname));
    }

    @ApiOperation("최근 본 목록 호출")
    @GetMapping("/recently-views")
    public Page<RecentlyViewedItemResponse> getRecentlyViewItems(@AuthenticationPrincipal AccountId accountId,
                                                             @RequestParam int size,
                                                             @RequestParam int page) {
        return memberFacade.getRecentlyViewItems(accountId, size, page);
    }

    @ApiOperation("최근 본 목록 추가")
    @PostMapping("/recently-views")
    public void addRecentlyView(@AuthenticationPrincipal AccountId accountId,
                                                                @RequestParam String itemType,
                                                                @RequestParam(required = false) Long itemId) {
        memberFacade.addRecentlyView(ItemType.valueOf(itemType), accountId, itemId);
    }

    @ApiOperation("최근 본 목록 삭제하기")
    @DeleteMapping("/recently-view")
    public void deleteRecentlyView(@AuthenticationPrincipal AccountId accountId,
                                   @RequestParam String itemType,
                                   @RequestParam(required = false) Long itemId) {
        memberFacade.deleteRecentlyView(ItemType.valueOf(itemType), accountId, itemId);
    }

    @ApiOperation("찜 목록 호출")
    @GetMapping("/favorite-items")
    public Page<RecentlyViewedItemResponse> getFavoriteItems(@AuthenticationPrincipal AccountId accountId,
                                                             @RequestParam int size,
                                                             @RequestParam int page) {
        return memberFacade.getFavoriteItems(accountId, size, page);
    }

    @ApiOperation("찜 목록 추가")
    @PostMapping("/favorite-item")
    public void addFavoriteItem(@AuthenticationPrincipal AccountId accountId,
                                     @RequestParam String itemType,
                                     @RequestParam(required = false) Long itemId) {
        memberFacade.addFavoriteItem(ItemType.valueOf(itemType), accountId, itemId);
    }

    @ApiOperation("찜 목록 삭제하기")
    @DeleteMapping("/favorite-item")
    public void deleteFavoriteItem(@AuthenticationPrincipal AccountId accountId,
                                   @RequestParam String itemType,
                                   @RequestParam(required = false) Long itemId) {
        memberFacade.deleteFavoriteItem(ItemType.valueOf(itemType), accountId, itemId);
    }

    @ApiOperation("멤버 로그아웃")
    @PostMapping("/member-logout")
    public void logout(@AuthenticationPrincipal AccountId accountId, @RequestBody MemberLogoutRequest logoutRequest) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
//        memberService.logout(accountId, member.get().getId(), OSType.valueOf(logoutRequest.getOsType()), logoutRequest.getDeviceId());
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
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        MemberId memberIdQuery = member.getId();
        return memberQueries.findMyProfileByMemberId(memberIdQuery);
    }

    @ApiOperation("좋아요 실행")
    @PostMapping("/like")
    public CommonResponse<Object> addLike(@AuthenticationPrincipal AccountId accountId,
                        @RequestParam String likeType,
                        @RequestParam Long itemId) throws InterruptedException {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        memberLikeService.addLike(MemberLikeCommand.request(
                member.getId(),
                likeType,
                itemId
        ));
        int likeCount = memberLikeQueries.findByMemberNickName(MemberLikeCommand.request(
                member.getId(),
                likeType,
                itemId));

        return CommonResponse.success(likeCount);
    }
}
