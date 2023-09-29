package com.repill.was.global.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class ImageListData implements Serializable {
    private List<String> images;

    public ImageListData(List<String> images) {
        this.images = images;
    }

    static public ImageListData from(List<String> images) {
        return Optional.ofNullable(images)
                .map(ImageListData::new)
                .orElse(null);
    }
}
