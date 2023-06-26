package com.repill.was.member.entity;

import com.repill.was.global.shard.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.util.StringUtils;

import javax.persistence.AttributeConverter;

@Slf4j
public class MemberFollowerListConverter implements AttributeConverter<MemberFollowerListData, String> {
    @Override
    public String convertToDatabaseColumn(MemberFollowerListData attribute) {
        if (attribute == null) {
            return "";
        }
        return JsonUtils.toJson(attribute);
    }

    @Override
    public MemberFollowerListData convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return null;
        }
        try {
            return JsonUtils.fromJson(dbData, MemberFollowerListData.class);
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }
}