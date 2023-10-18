package com.repill.was.member.controller.command;

import com.repill.was.global.exception.BadRequestException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.logstash.logback.util.StringUtils;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MemberDeleteCommand {

    @ApiModelProperty(notes = "서비스 정책에 따른 사유: ex) CAR1")
    private List<String> type;

    @ApiModelProperty(notes = "직접 입력 사유")
    private String additionalInformation;

    public MemberDeleteCommand(List<String> type, String additionalInformation) {
        this.type = type;
        this.additionalInformation = additionalInformation;
    }

    public static MemberDeleteCommand request(List<String> type, String additionalInformation) {
        if (type.isEmpty() && StringUtils.isEmpty(additionalInformation)) {
            throw new BadRequestException();
        }
        return new MemberDeleteCommand(type, additionalInformation);
    }
}
