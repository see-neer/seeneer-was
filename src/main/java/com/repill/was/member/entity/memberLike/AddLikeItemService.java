package com.repill.was.member.entity.memberLike;

import com.repill.was.global.enums.ItemType;
import com.repill.was.global.factory.itemvalidate.ItemValidateFactory;
import com.repill.was.global.factory.itemvalidate.ItemValidator;
import com.repill.was.global.factory.likevalidate.LikeValidateFactory;
import com.repill.was.global.factory.likevalidate.LikeValidator;
import com.repill.was.member.entity.memberLike.MemberLike.LikeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class AddLikeItemService {

    private final LikeValidateFactory likeValidateFactory;

    public void addLike(Long itemId, LikeType itemType, MemberLike newMemberLike) {
        LikeValidator validatorBy = likeValidateFactory.getValidatorBy(itemType);
        validatorBy.addLike(itemId, newMemberLike);
    }
}
