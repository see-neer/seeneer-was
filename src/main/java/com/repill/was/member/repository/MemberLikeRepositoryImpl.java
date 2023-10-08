package com.repill.was.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberRepository;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeId;
import com.repill.was.member.entity.memberLike.MemberLikeRepository;
import com.repill.was.member.entity.memberLike.QMemberLike;
import com.repill.was.member.repository.jpa.MemberJpaRepository;
import com.repill.was.member.repository.jpa.MemberLikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

import static com.repill.was.member.entity.account.QAccount.account;
import static com.repill.was.member.entity.member.QMember.member;
import static com.repill.was.member.entity.memberLike.QMemberLike.memberLike;

@Repository
@RequiredArgsConstructor
public class MemberLikeRepositoryImpl implements MemberLikeRepository {

    private final SequenceGenerator sequenceGenerator;
    private final MemberLikeJpaRepository memberLikeJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public MemberLikeId nextId() {
        return new MemberLikeId(sequenceGenerator.generate());
    }

    @Override
    public MemberLike save(MemberLike member) {
        return memberLikeJpaRepository.save(member);
    }

    @Override
    public void delete(MemberLike member) {
        memberLikeJpaRepository.delete(member);
    }

    @Override
    public Optional<MemberLike> findByItemIdAndMemberId(String itemType, MemberId memberId, Long itemId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(memberLike)
                .from(memberLike)
                .where(memberLike.itemId.eq(itemId)
                        .and(memberLike.likeType.eq(MemberLike.LikeType.valueOf(itemType)))
                        .and(memberLike.memberId.eq(memberId)))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .fetchOne());
    }

    @Override
    public int findTotalLikeCount(String itemType, MemberId memberId, Long itemId) {
  return 1;
    }
}
