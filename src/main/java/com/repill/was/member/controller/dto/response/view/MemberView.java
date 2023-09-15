package com.repill.was.member.controller.dto.response.view;

import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberView {

    private Long memberId;
    private String name;
    private String imageSrc;

    public MemberView(Long memberId, String nickName, String imageSrc) {
        this.memberId = memberId;
        this.name = nickName;
        this.imageSrc = imageSrc;
    }
}
