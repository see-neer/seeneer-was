package com.repill.was.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class MemberFollowerListData implements Serializable {
    private List<String> memberList;

    public MemberFollowerListData(List<String> memberList) {
        this.memberList = memberList;
    }

    static public MemberFollowerListData from(List<String> memberList) {
        return Optional.ofNullable(memberList)
                .map(MemberFollowerListData::new)
                .orElse(null);
    }
}
