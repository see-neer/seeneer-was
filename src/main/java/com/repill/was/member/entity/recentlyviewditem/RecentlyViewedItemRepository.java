package com.repill.was.member.entity.recentlyviewditem;

import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;

import java.util.Optional;

public interface RecentlyViewedItemRepository {

    MemberId nextId();

    Member save(Member member);

    Optional<Member> findById(MemberId memberId);

    Optional<Member> findByAccountId(AccountId accountId);

    Optional<Member> findByMemberNickName(String nickName);
}
