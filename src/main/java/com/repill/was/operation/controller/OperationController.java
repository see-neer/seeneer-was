package com.repill.was.operation.controller;


import com.repill.was.global.utils.SingleDataResponse;
import com.repill.was.global.enums.ClosingAccountReason;
import com.repill.was.operation.controller.dto.response.ClosingAccountReasonResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@OperationV1Controller
@RequiredArgsConstructor
public class OperationController {

    @ApiOperation("탈퇴 사유 목록")
    @GetMapping("/close-account-reasons")
    public SingleDataResponse<List<ClosingAccountReasonResponse>> getClosingAccountReasons() {
        List<ClosingAccountReasonResponse> reasons = ClosingAccountReason.allCases()
                .stream().map(reason -> ClosingAccountReasonResponse.from(reason, Locale.KOREA))
                .collect(Collectors.toList());

        return new SingleDataResponse<>(reasons);
    }
}
