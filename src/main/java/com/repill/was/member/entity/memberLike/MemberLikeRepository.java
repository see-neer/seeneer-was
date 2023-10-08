package com.repill.was.member.entity.memberLike;

import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;

import java.util.Optional;

public interface MemberLikeRepository {

    MemberLikeId nextId();

    MemberLike save(MemberLike member);

    void delete(MemberLike member);

    Optional<MemberLike> findByItemIdAndMemberId(String itemType, MemberId memberId, Long itemId);

    int findTotalLikeCount(String itemType, MemberId memberId, Long itemId);
}
