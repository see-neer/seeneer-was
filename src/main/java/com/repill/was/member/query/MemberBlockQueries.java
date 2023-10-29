package com.repill.was.member.query;

import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberblock.MemberBlock;
import com.repill.was.member.entity.memberblock.MemberBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberBlockQueries {

    private final MemberBlockRepository memberBlockRepository;

    @Transactional(readOnly = true)
    public List<MemberBlock> getAllMemberBlockList(MemberId memberId) {
        return memberBlockRepository.getAllMemberBlockList(memberId);
    }
}
