package com.repill.was.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @EmbeddedId
    MemberId id;

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
