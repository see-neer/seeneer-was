package com.repill.was.member.entity.memberLike;

import com.repill.was.global.event.Event;
import com.repill.was.member.entity.memberLike.AddLikeItemService;
import com.repill.was.member.entity.memberLike.MemberLike;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class MemberAddedLikeEvent extends Event {
    private MemberLike.LikeType likeType;
    private Long itemId;
    private MemberLike newMemberLike;

    public MemberAddedLikeEvent(MemberLike.LikeType likeType, Long itemId, MemberLike newMemberLike) {
        super();
        this.likeType = likeType;
        this.itemId = itemId;
        this.newMemberLike = newMemberLike;
    }
}
