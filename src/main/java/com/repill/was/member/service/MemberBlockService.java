package com.repill.was.member.service;

import com.repill.was.global.exception.BadRequestException;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberblock.MemberBlock;
import com.repill.was.member.entity.memberblock.MemberBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    @Transactional
    public void unblockMemberIfNeeded(MemberId memberId, MemberId blockMemberId) {
        Optional<MemberBlock> byMemberIdAndTargetMemberId = memberBlockRepository.findByMemberIdAndTargetMemberId(memberId, blockMemberId);
        if (byMemberIdAndTargetMemberId.isEmpty()) {
            return;
        }
        memberBlockRepository.delete(byMemberIdAndTargetMemberId.get());
    }
}
