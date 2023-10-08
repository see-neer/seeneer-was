package com.repill.was.member.controller.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.repill.was.member.controller.dto.request.MemberLoginRequest;
import com.repill.was.member.entity.account.AccountId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginCommand {

    private long id;

    private String deviceId;

    private AccountId accountId;

    private String nickname;

    private String profileImage;

    private String ageRange;

    private String birthday;

    private String birthdayType;

    private String gender;

    private String connectedAt;

    public LoginCommand(long id, String deviceId, AccountId accountId, String nickname, String profileImage, String ageRange, String birthday, String birthdayType, String gender, String connectedAt) {
        this.id = id;
        this.deviceId = deviceId;
        this.accountId = accountId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.ageRange = ageRange;
        this.birthday = birthday;
        this.birthdayType = birthdayType;
        this.gender = gender;
        this.connectedAt = connectedAt;
    }

    public static LoginCommand request(MemberLoginRequest memberLoginRequest, AccountId accountId) {
        return new LoginCommand(
                memberLoginRequest.getId(),
                memberLoginRequest.getDeviceId(),
                accountId,
                memberLoginRequest.getKakaoAccount().getProfile().getNickname(),
                memberLoginRequest.getKakaoAccount().getProfile().getProfileImage(),
                memberLoginRequest.getKakaoAccount().getAgeRange(),
                memberLoginRequest.getKakaoAccount().getBirthday(),
                memberLoginRequest.getKakaoAccount().getBirthdayType(),
                memberLoginRequest.getKakaoAccount().getGender(),
                memberLoginRequest.getConnectedAt()
        );
    }

//    response가 있다면 구현
//    public static LoginCommand response() {};
}
