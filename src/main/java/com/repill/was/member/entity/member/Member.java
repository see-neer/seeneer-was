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

    @Column(columnDefinition = "VARCHAR(50)")
    private String name;

    public Member(MemberId memberId, String name) {
        this.id = memberId;
        this.name = name;
    }
}
