package com.repill.was.global.shard.infra;

import com.repill.was.global.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {SwaggerConfig.SwaggerTags.ETC})
@RestController
@RequiredArgsConstructor
public class HeathCheckController {

    @ApiOperation("배포 확인용")
    @GetMapping("/health-check")
    public String healCheck() {
        return "ok";
    }
}
