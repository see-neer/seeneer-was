package com.repill.was.member.controller.dto.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberBlockListView {
    @ApiModelProperty(notes = "로그인 한 사용자의 차단 유저 리스트")
    private List<Long> blockedMemberList;

    public MemberBlockListView(List<Long> blockedMemberList) {
        this.blockedMemberList = blockedMemberList;
    }

    public boolean checkIfMemberBlocked(Long targetMemberId) {
        return this.getBlockedMemberList().contains(targetMemberId);
    }
}
