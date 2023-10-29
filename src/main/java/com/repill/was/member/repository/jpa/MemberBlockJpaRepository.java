package com.repill.was.member.repository.jpa;

import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeId;
import com.repill.was.member.entity.memberblock.MemberBlock;
import com.repill.was.member.entity.memberblock.MemberBlockId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberBlockJpaRepository extends JpaRepository<MemberBlock, MemberBlockId> {

    Optional<MemberBlock> findByMemberIdAndTargetMemberId(MemberId memberId, MemberId targetMemberId);
    List<MemberBlock> findByMemberId(MemberId memberId);
}
