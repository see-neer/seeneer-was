package com.repill.was.member.facade;

import com.repill.was.global.config.JwtTokenProvider;
import com.repill.was.member.controller.dto.MemberLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFacade {

    private final JwtTokenProvider jwtTokenProvider;

    public String login(MemberLoginRequest request) {
        return "22";
    }
}
