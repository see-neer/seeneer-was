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
//        UserLoginVO userByIdAndPassword = userService.findUserByLoginIdAndPassword(
//                        request.userId(),
//                        SHAUtil.encrypt(request.userPassword()))
//                .orElseThrow(() -> new BadRequestException("아이디 및 패스워드를 다시 확인해 주십시오."));
//
//        UserTypeVO userType = userService.findUserTypeByUserId(
//                        userByIdAndPassword.getUserId())
//                .orElseThrow(() -> new BadRequestException("사용자가 존재하지 않습니다."));

        String accessToken = jwtTokenProvider.createToken("test33344", "ADMI");
//        return UserLoginResponse.from(userType.getUserId(), accessToken);
        return accessToken;
    }
}
