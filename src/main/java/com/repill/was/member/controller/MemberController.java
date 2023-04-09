package com.repill.was.member.controller;

import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.shard.response.CommonResponse;
import com.repill.was.member.controller.dto.MemberLoginRequest;
import com.repill.was.member.facade.MemberFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {SwaggerConfig.SwaggerTags.MEMBER})
@RestController
@MemberV1Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;

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
}
