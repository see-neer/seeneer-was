package com.repill.was.global.shard.infra;

import com.repill.was.global.config.JwtTokenProvider;
import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.shard.infra.dto.AutoLoginRequest;
import com.repill.was.global.shard.infra.dto.HealthCheckResponse;
import com.repill.was.member.controller.dto.view.MemberView;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountRepository;
import com.repill.was.member.entity.account.OSType;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = {SwaggerConfig.SwaggerTags.ETC})
@RestController
@RequiredArgsConstructor
public class HeathCheckController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;

    @ApiOperation("설정 확인 용 Health Check. X-APP-VERSION (예: 0.0.5), X-APP-OS: IOS / ANDROID")
    @PostMapping("/health")
    public HealthCheckResponse healthCheck(@RequestHeader(value="X-APP-VERSION", required = false) String appVersion,
                                           @RequestHeader(value="X-APP-OS", required = false) String appOS,
                                           @RequestBody AutoLoginRequest autoLoginRequest) {
        Optional<Account> accountByDevice = accountRepository.findByDeviceId(autoLoginRequest.getDeviceId());
        MemberView memberView = new MemberView(Member.notExistMember());
        boolean exsistMember = false;

        if(accountByDevice.isEmpty()) {
            AccountId accountId = accountRepository.nextId();
            String token = jwtTokenProvider.createToken(accountId.getId().toString(), "ALL");
            accountRepository.save(Account.newOne(
                    accountId,
                    OSType.valueOf(appOS),
                    autoLoginRequest.getDeviceId()
            ));
            return new HealthCheckResponse(exsistMember, memberView, token);
        }

        String token = jwtTokenProvider.createToken(accountByDevice.get().getId().toString(), "ALL");
        Optional<Member> memberByAccountId = memberRepository.findByAccountId(accountByDevice.get().getId());
        if(memberByAccountId.isPresent()) {
            memberView = new MemberView(memberByAccountId.get());
            exsistMember = true;
        }
        return new HealthCheckResponse(exsistMember, memberView, token);
    }
}
