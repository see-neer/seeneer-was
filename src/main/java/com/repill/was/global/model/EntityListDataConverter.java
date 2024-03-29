package com.repill.was.global.model;

import com.repill.was.global.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;

@Slf4j
public class EntityListDataConverter implements AttributeConverter<EntityListData, String> {
    @Override
    public String convertToDatabaseColumn(EntityListData attribute) {
        if (attribute == null) {
            return "";
        }
        return JsonUtils.toJson(attribute);
    }

    @Override
    public EntityListData convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return null;
        }
        try {
            return JsonUtils.fromJson(dbData, EntityListData.class);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }
}