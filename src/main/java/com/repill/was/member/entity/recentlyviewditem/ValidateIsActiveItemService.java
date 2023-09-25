package com.repill.was.member.entity.recentlyviewditem;

import com.repill.was.member.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ValidateIsActiveItemService {

    public boolean canDeleteItem(Member member) {
        if(member.getClosedAt() == null) {
            return false;
        }
        return true;
    }
}
