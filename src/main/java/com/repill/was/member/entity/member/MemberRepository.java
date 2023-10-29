package com.repill.was.member.entity.member;

import com.repill.was.member.entity.account.AccountId;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    MemberId nextId();

    Member save(Member member);

    void delete(Member member);

    Optional<Member> findById(MemberId memberId);

    Optional<Member> findByAccountId(AccountId accountId);

    Optional<Member> findByKaKaoId(Long kakaoId);

    Optional<Member> findBannedExistByDeviceId(AccountId accountId, String deviceId);

    Optional<Member> findByMemberNickName(String nickName);
}
