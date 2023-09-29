package com.repill.was.global.factory;

import com.repill.was.global.enums.ItemType;
import com.repill.was.review.entity.ReviewItemTypeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemValidateFactory {

    private final List<ItemValidator> itemValidators;

    public ItemValidator getValidatorBy(ItemType type) {
        return itemValidators.stream()
                .filter(itemValidator -> itemValidator.getSupportType().equals(type))
                .findFirst().orElseThrow(ReviewItemTypeNotFoundException::new);
    }
}
