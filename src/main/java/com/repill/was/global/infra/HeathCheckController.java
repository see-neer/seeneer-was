package com.repill.was.global.infra;

import com.repill.was.global.config.JwtTokenProvider;
import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.enums.OSType;
import com.repill.was.global.infra.dto.request.AutoLoginRequest;
import com.repill.was.global.infra.dto.response.HealthCheckResponse;
import com.repill.was.member.controller.dto.response.view.MemberView;
import com.repill.was.member.entity.account.Account;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.AccountRepository;
import com.repill.was.member.entity.account.Device;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberRepository;
import com.repill.was.member.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = {SwaggerConfig.SwaggerTags.MAIN})
@RestController
@RequiredArgsConstructor
public class HeathCheckController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;

    @ApiOperation("설정 확인 용 Health Check. X-APP-VERSION (예: 0.0.5), X-APP-OS: IOS / ANDROID")
    @PostMapping("/health")
    public HealthCheckResponse healthCheck(@RequestHeader(value="X-APP-OS", required = false) String appOS,
                                           @RequestBody AutoLoginRequest autoLoginRequest) {
        Optional<Account> accountByDevice = accountRepository.findByDevice(new Device());

        if(accountByDevice.isEmpty()) {
            AccountId accountId = accountRepository.nextId();
            String token = jwtTokenProvider.createToken(accountId.getId().toString(), "ALL");
            accountRepository.save(Account.newOne(
                    accountId,
                    OSType.valueOf(appOS),
                    autoLoginRequest.getDeviceId(),
                    token
            ));
            return new HealthCheckResponse(false, MemberView.makeEmptyMemberView(), token);
        }

        MemberView memberView = null;
        boolean exsistMember = false;

        String token = jwtTokenProvider.createToken(accountByDevice.get().getId().toString(), "ALL");
        Optional<Member> memberByAccountId = memberRepository.findByAccountId(accountByDevice.get().getId());
        if(memberByAccountId.isPresent()) {
            Member member = memberByAccountId.get();
            memberView = new MemberView(member.getId().getId(), member.getNickname(), member.getImageSrc());
            exsistMember = true;
        }
        accountByDevice.get().changeDeviceInfo(OSType.valueOf(appOS),
                autoLoginRequest.getDeviceId(),
                token);
        accountRepository.save(accountByDevice.get());

        return new HealthCheckResponse(exsistMember, memberView, token);
    }
}
