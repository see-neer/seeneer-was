package com.repill.was.member.controller.dto.response.view;

import com.repill.was.member.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberView {

    private String name;
    private String imageSrc;

    public MemberView(Member member) {
        this.name = member.getNickname();
        this.imageSrc = member.getImageSrc();
    }
}
