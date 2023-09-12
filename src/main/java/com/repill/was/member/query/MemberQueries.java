package com.repill.was.member.query;

import com.repill.was.global.exception.BadRequestException;
import com.repill.was.member.controller.dto.response.MemberDetailProfileResponse;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueries {

    private final MemberRepository memberRepository;

    public Optional<Member> findById(MemberId id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByAccountId(AccountId id) {
        return memberRepository.findByAccountId(id);
    }


    public Optional<Member> findByMemberNickName(String nickName) {
        return memberRepository.findByMemberNickName(nickName);
    }

    public MemberDetailProfileResponse findMyProfileByMemberId(MemberId memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(BadRequestException::new);
        return MemberDetailProfileResponse.fromMember(member);
    }
}
