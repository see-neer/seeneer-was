package com.repill.was.member.entity.member;

import com.repill.was.member.entity.account.AccountId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @EmbeddedId
    MemberId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "accountId", nullable = false))
    AccountId accountId;

    String address;

    String favoriteHobby;

    String favoriteSite;

    String imageSrc;

    @Column(columnDefinition = "VARCHAR(50)")
    private String name;


    public Member(String imageSrc, String name) {
        this.imageSrc = imageSrc;
        this.name = name;
    }

    public  Member(MemberId id, AccountId accountId, String address, String favoriteHobby, String favoriteSite, String name) {
        this.id = id;
        this.accountId = accountId;
        this.address = address;
        this.favoriteHobby = favoriteHobby;
        this.favoriteSite = favoriteSite;
        this.name = name;
    }

    public static Member notExistMember() {
        return new Member(null, null);
    }
}
