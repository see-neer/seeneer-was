package com.repill.was.global.factory.likevalidate;

import com.repill.was.item.entity.ItemTypeNotFoundException;
import com.repill.was.member.entity.memberLike.MemberLike.LikeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LikeValidateFactory {

    private final List<LikeValidator> likeValidators;

    public LikeValidator getValidatorBy(LikeType type) {
        return likeValidators.stream()
                .filter(likeValidator -> likeValidator.getSupportType().equals(type))
                .findFirst().orElseThrow(ItemTypeNotFoundException::new);
    }
}
