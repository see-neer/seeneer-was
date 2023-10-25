package com.repill.was.member.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberAddInformationRequest {

    private List<String> interestingCategoryList;
    private List<String> interestingAddress;
    private String myAddressInfo;
}
