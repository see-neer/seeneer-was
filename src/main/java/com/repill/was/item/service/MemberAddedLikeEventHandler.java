package com.repill.was.item.service;

import com.repill.was.global.factory.likevalidate.LikeValidateFactory;
import com.repill.was.global.factory.likevalidate.LikeValidator;
import com.repill.was.member.entity.memberLike.MemberAddedLikeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberAddedLikeEventHandler {
    private final LikeValidateFactory likeValidateFactory;

    @EventListener(MemberAddedLikeEvent.class)
    public void handle(MemberAddedLikeEvent event) {
        LikeValidator validatorBy = likeValidateFactory.getValidatorBy(event.getLikeType());
        validatorBy.addLike(event.getItemId(), event.getNewMemberLike());
    }
}
