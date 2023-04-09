package com.repill.was.member.repository.jpa;

import com.repill.was.member.entity.Member;
import com.repill.was.member.entity.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, MemberId> {
    Member findByMemberId(MemberId memberId);
}
