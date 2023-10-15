package com.repill.was.global.infra;

import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.enums.MainMenu;
import com.repill.was.global.infra.dto.response.MainResponse;
import com.repill.was.member.entity.account.AccountId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {SwaggerConfig.SwaggerTags.MAIN})
@RestController
@RequiredArgsConstructor
public class MainController {

    @ApiOperation("메인 화면 호출")
    @GetMapping("/main")
    public MainResponse main(@AuthenticationPrincipal AccountId accountId) {
        List<MainResponse.CategoryView> categoryList = Arrays.stream(MainMenu.values()).map(one -> new MainResponse.CategoryView(one.getDescription(), one.getSubDescription(), one.getIsOpen())).collect(Collectors.toList());
        return MainResponse.from(categoryList);
    }
}
