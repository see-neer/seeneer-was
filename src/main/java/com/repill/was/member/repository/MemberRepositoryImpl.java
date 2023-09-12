package com.repill.was.member.repository;

import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberRepository;
import com.repill.was.member.repository.jpa.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final SequenceGenerator sequenceGenerator;
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public MemberId nextId() {
        return new MemberId(sequenceGenerator.generate());
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
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
    public Optional<Member> findByMemberNickName(String nickName) {
        return memberJpaRepository.findByNickname(nickName);
    }
}
