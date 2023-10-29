package com.repill.was.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.account.QAccount;
import com.repill.was.member.entity.member.*;
import com.repill.was.member.repository.jpa.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.repill.was.member.entity.account.QAccount.account;
import static com.repill.was.member.entity.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final SequenceGenerator sequenceGenerator;
    private final MemberJpaRepository memberJpaRepository;

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public MemberId nextId() {
        return new MemberId(sequenceGenerator.generate());
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public void delete(Member member) {
        memberJpaRepository.delete(member);
    }

    @Override
    public Optional<Member> findById(MemberId memberId) {
        return memberJpaRepository.findById(memberId);
    }

    @Override
    public Optional<Member> findByAccountId(AccountId accountId) {
        return memberJpaRepository.findByAccountId(accountId);
    }

    @Override
    public Optional<Member> findByKaKaoId(Long kakaoId) {
        return memberJpaRepository.findByKakaoUserId(kakaoId);
    }

    @Override
    public Optional<Member> findBannedExistByDeviceId(AccountId accountId, String deviceId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(member)
                .from(member)
                .leftJoin(account).on(member.accountId.eq(account.id))
                .where(member.bannedAt.isNotNull()
                        .and(account.device.deviceId.eq(deviceId)))
                .fetchOne());
    }

    @Override
    public Optional<Member> findByMemberNickName(String nickName) {
        return memberJpaRepository.findByNickname(nickName);
    }
}
