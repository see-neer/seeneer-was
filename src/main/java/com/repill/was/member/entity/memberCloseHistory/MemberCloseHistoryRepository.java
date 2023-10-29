package com.repill.was.member.entity.memberCloseHistory;

import com.repill.was.member.entity.member.MemberId;

import java.util.Optional;

public interface MemberCloseHistoryRepository {
    MemberCloseHistory save(MemberCloseHistory memberCloseHistory);
    Optional<MemberCloseHistory> findById(MemberId id);
}
