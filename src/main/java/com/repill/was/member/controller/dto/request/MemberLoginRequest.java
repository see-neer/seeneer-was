package com.repill.was.member.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLoginRequest {
    private long id;

    @JsonProperty("properties")
    private Properties properties;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @JsonProperty("connected_at")
    private String connectedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public KakaoAccount getKakaoAccount() {
        return kakaoAccount;
    }

    public void setKakaoAccount(KakaoAccount kakaoAccount) {
        this.kakaoAccount = kakaoAccount;
    }

    public String getConnectedAt() {
        return connectedAt;
    }

    public void setConnectedAt(String connectedAt) {
        this.connectedAt = connectedAt;
    }

    public static class Properties {
        private String nickname;

        @JsonProperty("profile_image")
        private String profileImage;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profile_image) {
            this.profileImage = profile_image;
        }
    }

    public static class KakaoAccount {
        @JsonProperty("profile_nickname_needs_agreement")
        private boolean profileNicknameNeedsAgreement;

        private Profile profile;

        @JsonProperty("age_range_needs_agreement")
        private boolean ageRangeNeedsAgreement;

        @JsonProperty("age_range")
        private String ageRange;

        @JsonProperty("birthday_needs_agreement")
        private boolean birthdayNeedsAgreement;

        private String birthday;

        @JsonProperty("birthday_type")
        private String birthdayType;

        @JsonProperty("gender_needs_agreement")
        private boolean genderNeedsAgreement;

        private String gender;

        public boolean isProfileNicknameNeedsAgreement() {
            return profileNicknameNeedsAgreement;
        }

        public void setProfileNicknameNeedsAgreement(boolean profileNicknameNeedsAgreement) {
            this.profileNicknameNeedsAgreement = profileNicknameNeedsAgreement;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }

        public boolean isAgeRangeNeedsAgreement() {
            return ageRangeNeedsAgreement;
        }

        public void setAgeRangeNeedsAgreement(boolean ageRangeNeedsAgreement) {
            this.ageRangeNeedsAgreement = ageRangeNeedsAgreement;
        }

        public String getAgeRange() {
            return ageRange;
        }

        public void setAgeRange(String ageRange) {
            this.ageRange = ageRange;
        }

        public boolean isBirthdayNeedsAgreement() {
            return birthdayNeedsAgreement;
        }

        public void setBirthdayNeedsAgreement(boolean birthdayNeedsAgreement) {
            this.birthdayNeedsAgreement = birthdayNeedsAgreement;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getBirthdayType() {
            return birthdayType;
        }

        public void setBirthdayType(String birthdayType) {
            this.birthdayType = birthdayType;
        }

        public boolean isGenderNeedsAgreement() {
            return genderNeedsAgreement;
        }

        public void setGenderNeedsAgreement(boolean genderNeedsAgreement) {
            this.genderNeedsAgreement = genderNeedsAgreement;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

    public static class Profile {
        private String nickname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

}
