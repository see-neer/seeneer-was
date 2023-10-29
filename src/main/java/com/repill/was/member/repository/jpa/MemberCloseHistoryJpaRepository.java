package com.repill.was.member.repository.jpa;

import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberCloseHistory.MemberCloseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCloseHistoryJpaRepository extends JpaRepository<MemberCloseHistory, MemberId> {
}
