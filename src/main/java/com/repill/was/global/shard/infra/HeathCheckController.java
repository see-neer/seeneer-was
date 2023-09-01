package com.repill.was.global.shard.infra;

import com.repill.was.global.config.JwtTokenProvider;
import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.shard.infra.dto.AutoLoginRequest;
import com.repill.was.global.shard.infra.dto.HealthCheckResponse;
import com.repill.was.member.controller.dto.view.MemberView;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {SwaggerConfig.SwaggerTags.ETC})
@RestController
@RequiredArgsConstructor
public class HeathCheckController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @ApiOperation("배포 확인용")
    @GetMapping("/health-check")
    public String healCheck() {
        return "ok";
    }

    @ApiOperation("설정 확인 용 Health Check. X-APP-VERSION (예: 0.0.5), X-APP-OS: IOS / ANDROID")
    @GetMapping("/health")
    public HealthCheckResponse healthCheck(@RequestBody AutoLoginRequest autoLoginRequest) {
        if(memberRepository.findByAccountId(new AccountId(1L)).isEmpty()) {
            //유저 정보 추가
            String token = jwtTokenProvider.createToken("@", "@");
            return new HealthCheckResponse(false, null, token);
        }
        return new HealthCheckResponse(true, new MemberView(Member.notExistMember()), null);
    }
}
