package com.repill.was.member.query;

import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberfollwer.MemberFollower;
import com.repill.was.member.entity.memberfollwer.MemberFollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberFollowerQueries {

    private final MemberFollowerRepository memberFollowerRepository;

    public List<MemberFollower> findByMemberId(MemberId memberId) {
        return memberFollowerRepository.findByMemberId(memberId);
    }

    public List<MemberFollower> findFolloweredByMemberId(MemberId memberId) {
        return memberFollowerRepository.findFolloweredByMemberId(memberId);
    }

    public Optional<MemberFollower> findFolloweredByMemberIdAndFollowerId(MemberId memberId, MemberId followerId) {
        return memberFollowerRepository.findFolloweredByMemberIdAndFollowerId(memberId, followerId);
    }
}
