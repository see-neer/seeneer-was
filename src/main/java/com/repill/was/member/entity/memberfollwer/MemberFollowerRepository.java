package com.repill.was.member.entity.memberfollwer;

import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeId;

import java.util.List;
import java.util.Optional;

public interface MemberFollowerRepository {

    MemberFollowerId nextId();

    MemberFollower save(MemberFollower member);

    void delete(MemberFollower member);

    List<MemberFollower> findByMemberId(MemberId memberId);

    List<MemberFollower>  findFolloweredByMemberId(MemberId memberId);

    Optional<MemberFollower> findFolloweredByMemberIdAndFollowerId(MemberId memberId, MemberId followerId);
}
