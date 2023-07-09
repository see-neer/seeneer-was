package com.repill.was.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.shard.response.CommonResponse;
import com.repill.was.member.controller.dto.MemberLoginRequest;
import com.repill.was.member.entity.Member;
import com.repill.was.member.entity.MemberId;
import com.repill.was.member.entity.MemberRepository;
import com.repill.was.member.facade.MemberFacade;
import com.repill.was.member.service.MemberService;
import com.repill.was.member.webclient.TestWebClient;
import com.repill.was.member.webclient.dto.TestDto;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.repill.was.member.entity.QMember.member;

@Api(tags = {SwaggerConfig.SwaggerTags.MEMBER})
@MemberV1Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;
    private final TestWebClient testWebClient;

    @ApiOperation("카카오 로그인 화면")
    @GetMapping("/login/kakao-pahe")
    public String loginKakaoPage() {

        StringBuffer url = new StringBuffer();
        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + "07a92cc104bea6f61eec06b289f97596");
        url.append("&redirect_uri=http://localhost:8080/member/api/v1/kakao/callback");
        url.append("&response_type=code");
        return "redirect:" + url;
    }

//    @ApiOperation("카카오 로그인 실행")
//    @GetMapping(value = "/kakao/callback", produuces = "application/json")
//    public String loginKakao(@RequestParam("code") String code,
//                             HttpSession session) {
//
//        String login = testWebClient.login(code);
//        return login;
//    }
//
//    @ApiOperation("로그인")
//    @PostMapping("/login")
//    public CommonResponse<Object> login(@RequestBody MemberLoginRequest memberLoginRequest) {
//        String token = memberFacade.login(memberLoginRequest);
//        return CommonResponse.success(token);
//    }

    @ApiOperation("로그인")
    @PostMapping("/login")
    public CommonResponse<Object> login(@RequestBody MemberLoginRequest memberLoginRequest) {
        String login = testWebClient.login(memberLoginRequest.getAccessToken());
        return CommonResponse.success(login);
    }
}
