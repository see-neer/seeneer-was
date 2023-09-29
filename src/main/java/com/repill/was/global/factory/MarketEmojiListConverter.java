package com.repill.was.global.factory;

import com.repill.was.global.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.util.StringUtils;

import javax.persistence.AttributeConverter;

@Slf4j
public class MarketEmojiListConverter implements AttributeConverter<MarketEmojiListData, String> {
    @Override
    public String convertToDatabaseColumn(MarketEmojiListData attribute) {
        if (attribute == null) {
            return "";
        }
        return JsonUtils.toJson(attribute);
    }

    @Override
    public MarketEmojiListData convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return null;
        }
        try {
            return JsonUtils.fromJson(dbData, MarketEmojiListData.class);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }
}