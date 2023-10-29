package com.repill.was.member.service;

import com.repill.was.global.exception.BadRequestException;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberblock.MemberBlock;
import com.repill.was.member.entity.memberblock.MemberBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberBlockService {

    private final MemberBlockRepository memberBlockRepository;

    @Transactional
    public void blockMemberIfNeeded(MemberId memberId, MemberId blockMemberId) {
        if (memberBlockRepository.findByMemberIdAndTargetMemberId(memberId, blockMemberId).isPresent()) {
            return;
        }
        memberBlockRepository.save(MemberBlock.newOne(memberBlockRepository.nextId(), memberId, blockMemberId));
    }
}
