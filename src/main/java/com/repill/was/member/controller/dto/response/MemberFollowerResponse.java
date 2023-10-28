package com.repill.was.member.controller.dto.response;

import com.repill.was.member.controller.dto.response.view.MemberView;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberFollowerResponse {

    MemberView members;
    String followedAt;
    boolean isFollowered;

    public MemberFollowerResponse(MemberView members, String followedAt, boolean isFollowered) {
        this.members = members;
        this.followedAt = followedAt;
        this.isFollowered = isFollowered;
    }

    public static MemberFollowerResponse newOne(MemberView members, String followedAt, boolean isFollowered) {
        return new MemberFollowerResponse(members,
                followedAt,
                isFollowered);
    }
}
