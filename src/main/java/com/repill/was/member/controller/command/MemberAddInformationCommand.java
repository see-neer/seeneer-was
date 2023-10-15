package com.repill.was.member.controller.command;

import com.repill.was.global.enums.FavoriteCategory;
import com.repill.was.member.controller.dto.request.MemberAddInformationRequest;
import com.repill.was.member.entity.account.AccountId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberAddInformationCommand {

    private MemberAddInformationRequest.MemberAddress myAddressInfo;
    private List<String> interestingCategoryList;
    private MemberAddInformationRequest.MemberAddress interestingAddress;
    private AccountId accountId;

    public static MemberAddInformationCommand request(MemberAddInformationRequest addMemberInformationRequest, AccountId accountId){
        validateAddress(addMemberInformationRequest.getMyAddressInfo());
        validateAddress(addMemberInformationRequest.getInterestingAddress());
        validateCategory(addMemberInformationRequest.getInterestingCategoryList());
        return new MemberAddInformationCommand(addMemberInformationRequest.getMyAddressInfo(),
                addMemberInformationRequest.getInterestingCategoryList(),
                addMemberInformationRequest.getInterestingAddress(),
                accountId);
    }

    private static void validateAddress(MemberAddInformationRequest.MemberAddress address){
        //todo 구현필요
    }

    private static void validateCategory(List<String> interestingCategoryList){
        interestingCategoryList.forEach(FavoriteCategory::validateCategory);
    }
}

