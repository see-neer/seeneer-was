package com.repill.was.member.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberAddInformationRequest {

    private MemberAddress myAddressInfo;
    private List<String> interestingCategoryList;
    private MemberAddress interestingAddress;

    @Getter
    @NoArgsConstructor
    public static class MemberAddress {
        private String addressDetailA;
        private String addressDetailB;
        private String addressDetailC;
        private String addressDetailD;
    }
}
