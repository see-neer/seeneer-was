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
import com.repill.was.member.webclient.TestWebClient;
import com.repill.was.member.webclient.dto.TestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.repill.was.member.entity.QMember.member;

@Api(tags = {SwaggerConfig.SwaggerTags.MEMBER})
@RestController
@MemberV1Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;
    private final MemberRepository memberRepository;
    private final JPAQueryFactory queryFactory;

    private final TestWebClient testWebClient;

    @ApiOperation("로그인22")
    @PostMapping("/login22")
    public CommonResponse<Object> login2() {
        memberRepository.save(new Member(memberRepository.nextId(), "33"));
        return null;
    }

    @ApiOperation("로그인223")
    @PostMapping("/login223")
    public CommonResponse<Object> login23() {
        String s = queryFactory
                .select(member.name)
                .from(member)
                .fetchOne();
        return CommonResponse.success(s);
    }

    @ApiOperation("로그인")
    @PostMapping("/login")
    public CommonResponse<Object> login(@RequestBody MemberLoginRequest memberLoginRequest) {
        String token = memberFacade.login(memberLoginRequest);
        return CommonResponse.success(token);
    }

    @ApiOperation("테스트")
    @PostMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResponse<Object> test(@AuthenticationPrincipal String adminAccountId) {
        return CommonResponse.success(adminAccountId);
    }

    @ApiOperation("테스트")
    @PostMapping("/test/webclient")
    public TestDto testWebClient() throws URISyntaxException {
        return testWebClient.test();
    }

    @ApiOperation("test")
    @PostMapping("/slack-test")
    public CommonResponse<Object> test() throws Exception {
        throw new Exception("22");
    }
}
