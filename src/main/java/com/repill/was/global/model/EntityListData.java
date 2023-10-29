package com.repill.was.global.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class EntityListData implements Serializable {
    private List<String> images;

    public EntityListData(List<String> images) {
        this.images = images;
    }

    static public EntityListData from(List<String> images) {
        return Optional.ofNullable(images)
                .map(EntityListData::new)
                .orElse(null);
    }
}
