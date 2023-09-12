package com.repill.was.member.controller.dto.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberProfileUpdateRequest {
    @ApiModelProperty(notes = "닉네임. 변경전과 동일한 값 전달시 무시됨. 정책위반시 NicknameChangeNotAvailableException 발생 (에러메시지 포함)")
    @NotNull
    private String nickname;

    @ApiModelProperty(notes = "프로필 사진, 값 없을시 기등록 이미지 삭제됨")
    private String profileImageSrc;

    @ApiModelProperty(required = true, notes = "실명 숨기기 옵션 (true인경우 숨김 처리)")
    private boolean hideRealName;
}
