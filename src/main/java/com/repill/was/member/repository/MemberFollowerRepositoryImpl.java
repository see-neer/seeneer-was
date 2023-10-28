package com.repill.was.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeId;
import com.repill.was.member.entity.memberLike.MemberLikeRepository;
import com.repill.was.member.entity.memberfollwer.MemberFollower;
import com.repill.was.member.entity.memberfollwer.MemberFollowerId;
import com.repill.was.member.entity.memberfollwer.MemberFollowerRepository;
import com.repill.was.member.repository.jpa.MemberFollowerJpaRepository;
import com.repill.was.member.repository.jpa.MemberLikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

import static com.repill.was.member.entity.memberLike.QMemberLike.memberLike;

@Repository
@RequiredArgsConstructor
public class MemberFollowerRepositoryImpl implements MemberFollowerRepository {

    private final SequenceGenerator sequenceGenerator;
    private final MemberFollowerJpaRepository memberFollowerJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public MemberFollowerId nextId() {
        return new MemberFollowerId(sequenceGenerator.generate());
    }

    @Override
    public MemberFollower save(MemberFollower member) {
        return memberFollowerJpaRepository.save(member);
    }

    @Override
    public void delete(MemberFollower member) {
        memberFollowerJpaRepository.delete(member);
    }

    @Override
    public List<MemberFollower> findByMemberId(MemberId memberId) {
        return memberFollowerJpaRepository.findByMemberId(memberId);
    }

    @Override
    public List<MemberFollower>  findFolloweredByMemberId(MemberId memberId) {
        return memberFollowerJpaRepository.findByFollowerId(memberId);
    }

    @Override
    public Optional<MemberFollower> findFolloweredByMemberIdAndFollowerId(MemberId memberId, MemberId followerId) {
        return memberFollowerJpaRepository.findFolloweredByMemberIdAndFollowerId(memberId, followerId);
    }
}
