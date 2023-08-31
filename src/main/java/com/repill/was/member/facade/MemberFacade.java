package com.repill.was.member.facade;

import com.repill.was.global.config.JwtTokenProvider;
import com.repill.was.member.controller.dto.request.MemberLoginRequest;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.query.MemberQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberFacade {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberQueries memberQueries;

    public String login(MemberLoginRequest request) {
        return "22";
    }

    public Boolean checkDuplicateNickname(String insertedNickname, Boolean isUsedSocialName) {
        if(!isUsedSocialName) {
            return memberQueries.findByMemberNickName(insertedNickname).isPresent();
        }
        return false;
    }

    public void getRecentlyViewList(MemberId memberId) {
        return;
    }
}
