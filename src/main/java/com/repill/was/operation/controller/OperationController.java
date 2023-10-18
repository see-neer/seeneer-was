package com.repill.was.operation.controller;


import com.repill.was.global.enums.FavoriteCategory;
import com.repill.was.global.factory.NickNameMakeFactory;
import com.repill.was.global.utils.SingleDataResponse;
import com.repill.was.global.enums.ClosingAccountReason;
import com.repill.was.operation.controller.dto.response.ClosingAccountReasonResponse;
import com.repill.was.operation.controller.dto.response.FavoriteCategoryResponse;
import com.repill.was.operation.entity.AddressInfo;
import com.repill.was.operation.entity.AddressInfoRepository;
import com.repill.was.operation.entity.NickNameRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@OperationV1Controller
@RequiredArgsConstructor
public class OperationController {

    private final AddressInfoRepository addressInfoRepository;
    private final NickNameRepository nickNameRepository;

    @ApiOperation("탈퇴 사유 목록")
    @GetMapping("/close-account-reasons")
    public SingleDataResponse<List<ClosingAccountReasonResponse>> getClosingAccountReasons() {
        List<ClosingAccountReasonResponse> reasons = ClosingAccountReason.allCases()
                .stream().map(reason -> ClosingAccountReasonResponse.from(reason, Locale.KOREA))
                .collect(Collectors.toList());

        return new SingleDataResponse<>(reasons);
    }

    @ApiOperation("카테고리 목록")
    @GetMapping("/categories")
    public SingleDataResponse<List<FavoriteCategoryResponse>> getCategories() {
        List<FavoriteCategoryResponse> reasons = FavoriteCategory.allCases()
                .stream().map(categories -> FavoriteCategoryResponse.from(categories, Locale.KOREA))
                .collect(Collectors.toList());

        return new SingleDataResponse<>(reasons);
    }

    @ApiOperation("주소 목록")
    @GetMapping("/address")
    public List<AddressInfo> getAddress() {
        return addressInfoRepository.findAll();
    }

    @ApiOperation("랜덤 이름 생성")
    @GetMapping("/random-nick-name")
    public String getRandomNickName() {
        return NickNameMakeFactory.makeNickName(nickNameRepository);
    }
}
