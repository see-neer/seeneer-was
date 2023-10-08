package com.repill.was.member.query;

import com.repill.was.member.controller.command.MemberLikeCommand;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberRepository;
import com.repill.was.member.entity.memberLike.MemberLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberLikeQueries {

    private final MemberLikeRepository memberLikeRepository;

    public int findByMemberNickName(MemberLikeCommand memberLikeCommand) {
        return memberLikeRepository.findTotalLikeCount(memberLikeCommand.getLikeType().name(),
                memberLikeCommand.getMemberId(),
                memberLikeCommand.getItemId());
    }
}
