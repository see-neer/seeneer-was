package com.repill.was.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @EmbeddedId
    MemberId memberId;

    @Column(columnDefinition = "VARCHAR(50)")
    private String name;
}
