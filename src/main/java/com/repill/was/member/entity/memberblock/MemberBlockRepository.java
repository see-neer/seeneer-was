package com.repill.was.member.entity.memberblock;

import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeId;

import java.util.List;
import java.util.Optional;

public interface MemberBlockRepository {

    MemberBlockId nextId();

    MemberBlock save(MemberBlock member);

    void delete(MemberBlock member);

    Optional<MemberBlock> findByMemberIdAndTargetMemberId(MemberId memberId, MemberId targetMemberId);

    List<MemberBlock> getAllMemberBlockList(MemberId memberId);
}
