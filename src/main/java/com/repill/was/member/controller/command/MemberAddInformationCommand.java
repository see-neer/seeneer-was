package com.repill.was.member.controller.command;

import com.repill.was.global.enums.FavoriteCategory;
import com.repill.was.global.factory.addressvaildate.AddressValidateFactory;
import com.repill.was.member.controller.dto.request.MemberAddInformationRequest;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.InvalidCreditCardInfoException;
import com.repill.was.operation.entity.AddressInfoRepository;
import com.repill.was.operation.entity.AddressNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberAddInformationCommand {

    private String myAddressInfo;
    private List<String> interestingCategoryList;
    private List<String> interestingAddress;
    private AccountId accountId;
    private String nickname;

    public static MemberAddInformationCommand request(MemberAddInformationRequest addMemberInformationRequest, AddressInfoRepository addressInfoRepository, AccountId accountId){
        validateAddress(addressInfoRepository, List.of(addMemberInformationRequest.getMyAddressInfo()));
        validateAddress(addressInfoRepository, addMemberInformationRequest.getInterestingAddress());
        validateCategory(addMemberInformationRequest.getInterestingCategoryList());
        validateNickname(addMemberInformationRequest.getNickName());
        return new MemberAddInformationCommand(addMemberInformationRequest.getMyAddressInfo(),
                addMemberInformationRequest.getInterestingCategoryList(),
                addMemberInformationRequest.getInterestingAddress(),
                accountId,
                addMemberInformationRequest.getNickName());
    }

    private static void validateAddress(AddressInfoRepository addressInfoRepository, List<String> address){
        address.forEach(one -> {
            if(addressInfoRepository.findByAddressDetailA(one).isEmpty()) throw new AddressNotFoundException();
        });
    }

    private static void validateCategory(List<String> interestingCategoryList){
        interestingCategoryList.forEach(FavoriteCategory::validateCategory);
    }

    private static void validateNickname(String nickname) {
        String message = "별명 최소 2자, 최대 15자까지 가능합니다";
        if (StringUtils.isEmpty(nickname)) {
            throw new InvalidCreditCardInfoException(message);
        }
        if (nickname.length() < 2 || nickname.length() > 15) {
            throw new InvalidCreditCardInfoException(message);
        }
    }
}

