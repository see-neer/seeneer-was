package com.repill.was.member.controller;

import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.shard.enums.Category;
import com.repill.was.global.shard.response.CommonResponse;
import com.repill.was.member.controller.dto.request.CheckDuplicateNickNameRequest;
import com.repill.was.member.controller.dto.response.MainResponse;
import com.repill.was.member.controller.dto.request.MemberLoginRequest;
import com.repill.was.member.controller.dto.response.MainResponse.CategoryView;
import com.repill.was.member.controller.dto.response.RecentlyViewedItemResponse;
import com.repill.was.member.controller.dto.view.MemberView;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.facade.MemberFacade;
import com.repill.was.member.query.MemberQueries;
import com.repill.was.member.webclient.TestWebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {SwaggerConfig.SwaggerTags.MEMBER})
@MemberV1Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;
    private final MemberQueries memberQueries;

    // todo 23.08.31 odo 재경님 같이 작업
//    @ApiOperation("로그인")
//    @PostMapping("/login")
//    public CommonResponse<Object> login(@RequestBody MemberLoginRequest memberLoginRequest) {
//        String token = memberFacade.login(memberLoginRequest);
//        return CommonResponse.success(token);
//    }
//
    @ApiOperation("메인 화면 호출")
    @GetMapping("/main")
    public MainResponse main(@AuthenticationPrincipal AccountId accountId) {
        List<CategoryView> categoryList = Arrays.stream(Category.values()).map(one -> new CategoryView(one.getDescription(), one.getSubDescription())).collect(Collectors.toList());
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

//    @ApiOperation("찜 목록 호출")
//    @GetMapping("/check-duplicated-nickname")
//    public Boolean asd(String insertedNickname) {
//        return memberFacade.checkDuplicateNickname(insertedNickname);
//    }
//
//    @ApiOperation("찜 목록 삭제하기")
//    @GetMapping("/check-duplicated-nickname")
//    public Boolean xcv(String insertedNickname) {
//        return memberFacade.checkDuplicateNickname(insertedNickname);
//    }


//    @ApiOperation("카카오 로그인 실행")
//    @GetMapping(value = "/kakao/callback", produuces = "application/json")
//    public String loginKakao(@RequestParam("code") String code,
//                             HttpSession session) {
//
//        String login = testWebClient.login(code);
//        return login;
//    }
}
