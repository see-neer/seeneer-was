package com.repill.was.member.repository.jpa;

import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.FavoriteItem;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, MemberId> {

    Optional<Member> findByNickname(String nickName);

    Optional<Member> findByAccountId(AccountId accountId);
}
