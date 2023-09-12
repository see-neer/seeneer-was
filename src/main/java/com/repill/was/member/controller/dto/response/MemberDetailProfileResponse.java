package com.repill.was.member.controller.dto.response;

import com.repill.was.member.entity.member.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDetailProfileResponse {

    @ApiModelProperty(required = true, notes = "wewoot 멤버 id")
    private long id;

    @ApiModelProperty(required = true, notes = "닉네임")
    private String nickName;

    @ApiModelProperty(notes = "프로필 사진 주소")
    private String profileImageSrc;

    @ApiModelProperty(notes = "프로필 사진 주소")
    private String loginType;

    public MemberDetailProfileResponse(long id, String nickName, String profileImageSrc, String loginType) {
        this.id = id;
        this.nickName = nickName;
        this.profileImageSrc = profileImageSrc;
        this.loginType = loginType;
    }

    public static MemberDetailProfileResponse fromMember(Member member) {
        return new MemberDetailProfileResponse(
                member.getId().getId(),
                member.getNickname(),
                member.getImageSrc(),
                "KAKAO"
        );
    }
}
