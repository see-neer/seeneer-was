package com.repill.was.member.entity.memberLike;

import com.repill.was.global.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class MemberUnlikeEvent extends Event {
    private MemberLike.LikeType likeType;
    private Long itemId;
    private MemberLike deleteMemberLike;

    public MemberUnlikeEvent(MemberLike.LikeType likeType, Long itemId, MemberLike deleteMemberLike) {
        super();
        this.likeType = likeType;
        this.itemId = itemId;
        this.deleteMemberLike = deleteMemberLike;
    }
}
