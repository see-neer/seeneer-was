package com.repill.was.global.shard.model;

import com.repill.was.global.shard.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;

@Slf4j
public class ImageListDataConverter implements AttributeConverter<ImageListData, String> {
    @Override
    public String convertToDatabaseColumn(ImageListData attribute) {
        if (attribute == null) {
            return "";
        }
        return JsonUtils.toJson(attribute);
    }

    @Override
    public ImageListData convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return null;
        }
        try {
            return JsonUtils.fromJson(dbData, ImageListData.class);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }
}