package com.repill.was.member.controller.command;

import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.repill.was.member.entity.memberLike.MemberLike.*;

@Getter
@RequiredArgsConstructor
public class MemberLikeCommand {

    private MemberId memberId;
    private LikeType likeType;
    private Long itemId;

    public MemberLikeCommand(MemberId memberId, LikeType likeType, Long itemId) {
        this.memberId = memberId;
        this.likeType = likeType;
        this.itemId = itemId;
    }

    public static MemberLikeCommand request(MemberId memberId, String likeType, Long itemId) {
        return new MemberLikeCommand(
                memberId,
                LikeType.valueOf(likeType),
                itemId
        );
    }
}
