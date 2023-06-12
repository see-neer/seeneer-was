package com.repill.was.member.repository;

import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.Member;
import com.repill.was.member.entity.MemberId;
import com.repill.was.member.entity.MemberRepository;
import com.repill.was.member.repository.jpa.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public Member findByMemberId(MemberId memberId) {
        return memberJpaRepository.findById(memberId).get();
    }
}
