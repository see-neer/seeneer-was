package com.repill.was.member.controller.command;

import com.repill.was.member.controller.dto.request.MemberAddInformationRequest;
import com.repill.was.member.controller.dto.request.MemberProfileUpdateRequest;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.InvalidCreditCardInfoException;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.operation.entity.AddressInfoRepository;
import com.repill.was.operation.entity.AddressNotFoundException;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Getter
@RequiredArgsConstructor
public class MemberUpdateProfileCommand {
    private String nickname;
    private String profileImageSrc;
    private boolean hideRealName;

    public MemberUpdateProfileCommand(String nickname, String profileImageSrc, boolean hideRealName) {
        this.nickname = nickname;
        this.profileImageSrc = profileImageSrc;
        this.hideRealName = hideRealName;
    }

    public static MemberUpdateProfileCommand request(MemberProfileUpdateRequest memberProfileUpdateRequest) {
        return new MemberUpdateProfileCommand(
                validateNickname(memberProfileUpdateRequest.getNickname()),
                memberProfileUpdateRequest.getProfileImageSrc(),
                memberProfileUpdateRequest.isHideRealName()
        );
    }

    // todo 중복코드이므로 Factory Class 진행 필요
    private static String validateNickname(String nickname) {
        String message = "별명 최소 2자, 최대 15자까지 가능합니다";
        if (StringUtils.isEmpty(nickname)) {
            throw new InvalidCreditCardInfoException(message);
        }
        if (nickname.length() < 2 || nickname.length() > 15) {
            throw new InvalidCreditCardInfoException(message);
        }
        return nickname;
    }
}
