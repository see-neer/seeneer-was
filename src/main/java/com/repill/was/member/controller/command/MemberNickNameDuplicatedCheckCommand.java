package com.repill.was.member.controller.command;

import com.repill.was.member.entity.member.InvalidCreditCardInfoException;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberNickNameDuplicatedCheckCommand {

    private String checkNickName;
    private boolean useKakaoNickname;

    public static MemberNickNameDuplicatedCheckCommand request(String checkNickName, boolean useKakaoNickname) {
        validateNickname(checkNickName);
        return new MemberNickNameDuplicatedCheckCommand(checkNickName, useKakaoNickname);
    }

    private static void validateNickname(String nickname) {
        String message = "별명 최소 2자, 최대 15자까지 가능합니다";
        if (StringUtils.isEmpty(nickname)) {
            throw new InvalidCreditCardInfoException(message);
        }
        if (nickname.length() < 2 || nickname.length() > 15) {
            throw new InvalidCreditCardInfoException(message);
        }
    }
}
