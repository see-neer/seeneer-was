package com.repill.was.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeId;
import com.repill.was.member.entity.memberLike.MemberLikeRepository;
import com.repill.was.member.entity.memberblock.MemberBlock;
import com.repill.was.member.entity.memberblock.MemberBlockId;
import com.repill.was.member.entity.memberblock.MemberBlockRepository;
import com.repill.was.member.repository.jpa.MemberBlockJpaRepository;
import com.repill.was.member.repository.jpa.MemberLikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

import static com.repill.was.member.entity.memberLike.QMemberLike.memberLike;

@Repository
@RequiredArgsConstructor
public class MemberBlockRepositoryImpl implements MemberBlockRepository {

    private final SequenceGenerator sequenceGenerator;
    private final MemberBlockJpaRepository memberBlockJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public MemberBlockId nextId() {
        return new MemberBlockId(sequenceGenerator.generate());
    }

    @Override
    public MemberBlock save(MemberBlock member) {
        return memberBlockJpaRepository.save(member);
    }

    @Override
    public void delete(MemberBlock member) {
        memberBlockJpaRepository.delete(member);
    }

    @Override
    public Optional<MemberBlock> findByMemberIdAndTargetMemberId(MemberId memberId, MemberId targetMemberId) {
        return memberBlockJpaRepository.findByMemberIdAndTargetMemberId(memberId, targetMemberId);
    }

    @Override
    public List<MemberBlock> getAllMemberBlockList(MemberId memberId) {
        return memberBlockJpaRepository.findByMemberId(memberId);
    }
}
