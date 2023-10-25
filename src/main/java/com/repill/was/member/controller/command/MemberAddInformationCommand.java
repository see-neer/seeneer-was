package com.repill.was.member.controller.command;

import com.repill.was.global.enums.FavoriteCategory;
import com.repill.was.global.factory.addressvaildate.AddressValidateFactory;
import com.repill.was.member.controller.dto.request.MemberAddInformationRequest;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.operation.entity.AddressInfoRepository;
import com.repill.was.operation.entity.AddressNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberAddInformationCommand {

    private String myAddressInfo;
    private List<String> interestingCategoryList;
    private List<String> interestingAddress;
    private AccountId accountId;

    public static MemberAddInformationCommand request(MemberAddInformationRequest addMemberInformationRequest, AddressInfoRepository addressInfoRepository, AccountId accountId){
        validateAddress(addressInfoRepository, List.of(addMemberInformationRequest.getMyAddressInfo()));
        validateAddress(addressInfoRepository, addMemberInformationRequest.getInterestingAddress());
        validateCategory(addMemberInformationRequest.getInterestingCategoryList());
        return new MemberAddInformationCommand(addMemberInformationRequest.getMyAddressInfo(),
                addMemberInformationRequest.getInterestingCategoryList(),
                addMemberInformationRequest.getInterestingAddress(),
                accountId);
    }

    private static void validateAddress(AddressInfoRepository addressInfoRepository, List<String> address){
        address.forEach(one -> {
            addressInfoRepository.findByAddressDetailA(one).orElseThrow(AddressNotFoundException::new);
        });
    }

    private static void validateCategory(List<String> interestingCategoryList){
        interestingCategoryList.forEach(FavoriteCategory::validateCategory);
    }
}

