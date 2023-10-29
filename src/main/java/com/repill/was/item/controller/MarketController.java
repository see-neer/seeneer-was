package com.repill.was.item.controller;


import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.item.controller.dto.response.FestivalInfoResponse;
import com.repill.was.item.controller.dto.response.MarketInfoResponse;
import com.repill.was.item.facade.MarketFacade;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberNotFoundException;
import com.repill.was.member.query.MemberQueries;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Api(tags = {SwaggerConfig.SwaggerTags.MARKET})
@RestController
@MarketV1Controller
@RequiredArgsConstructor
public class MarketController {

    private final MemberQueries memberQueries;
    private final MarketFacade marketFacade;

    @ApiOperation("마케 정보 호출")
    @GetMapping("/lists")
    public Page<MarketInfoResponse> getLists(@AuthenticationPrincipal AccountId accountId,
                                               @RequestParam String addressDetailA,
                                               @RequestParam String addressDetailB,
                                               @RequestParam(required = false) String date,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        return marketFacade.getLists(addressDetailA, addressDetailB, date, page, size);
    }

    @ApiOperation("마켓 정보 상세 보기")
    @GetMapping("/{id}")
    public MarketInfoResponse getReviewDetail(@AuthenticationPrincipal AccountId accountId,
                                              @PathVariable("id") Long id,
                                              @RequestParam String addressDetailA,
                                              @RequestParam String addressDetailB,
                                              @RequestParam(required = false) String date) {
        memberQueries.findByAccountId(accountId).orElseThrow(() -> new BadRequestException("존재하지 않는 유저 입니다."));
        return marketFacade.getDetail(id, addressDetailA, addressDetailB, date);
    }
}
