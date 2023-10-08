package com.repill.was.member.service;

import com.repill.was.global.enums.ItemType;
import com.repill.was.member.controller.command.MemberLikeCommand;
import com.repill.was.member.entity.memberLike.AddLikeItemService;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberLikeService {

    private final MemberLikeRepository memberLikeRepository;
    private final AddLikeItemService addLikeItemService;


    @Transactional
    public void addLike(MemberLikeCommand memberLikeCommand){
        Optional<MemberLike> byItemIdAndMemberId = memberLikeRepository.findByItemIdAndMemberId(
                memberLikeCommand.getLikeType().name(),
                memberLikeCommand.getMemberId(),
                memberLikeCommand.getItemId()
        );

        if(byItemIdAndMemberId.isEmpty()) {
            MemberLike newMemberLike = MemberLike.newOne(
                    memberLikeRepository.nextId(),
                    memberLikeCommand.getMemberId(),
                    memberLikeCommand.getItemId(),
                    memberLikeCommand.getLikeType()
            );
            memberLikeRepository.save(newMemberLike);

            MemberLike.addLike(addLikeItemService, memberLikeCommand.getLikeType(), memberLikeCommand.getItemId(), newMemberLike);
        }
    }
}
