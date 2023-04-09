package com.repill.was.member.entity;

public interface MemberRepository {

    MemberId nextId();

    Member save(Member member);

    Member findByMemberId(MemberId memberId);
}
