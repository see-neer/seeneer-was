package com.repill.was.member.repository.jpa;

import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberfollwer.MemberFollower;
import com.repill.was.member.entity.memberfollwer.MemberFollowerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberFollowerJpaRepository extends JpaRepository<MemberFollower, MemberFollowerId> {
    List<MemberFollower> findByMemberId(MemberId memberId);
    List<MemberFollower> findByFollowerId(MemberId followerId);
    Optional<MemberFollower> findFolloweredByMemberIdAndFollowerId(MemberId memberId, MemberId followerId);
}
