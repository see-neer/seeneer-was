package com.repill.was.member.repository;

import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberCloseHistory.MemberCloseHistory;
import com.repill.was.member.entity.memberCloseHistory.MemberCloseHistoryRepository;
import com.repill.was.member.repository.jpa.MemberCloseHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberCloseHistoryRepositoryImpl implements MemberCloseHistoryRepository {
    private final MemberCloseHistoryJpaRepository memberCloseHistoryJpaRepository;

    @Override
    public MemberCloseHistory save(MemberCloseHistory memberCloseHistory) {
        return memberCloseHistoryJpaRepository.save(memberCloseHistory);
    }

    @Override
    public Optional<MemberCloseHistory> findById(MemberId id) {
        return memberCloseHistoryJpaRepository.findById(id);
    }
}