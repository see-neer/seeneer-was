package com.repill.was.item.controller;

import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.enums.ItemType;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.item.controller.dto.response.FestivalInfoResponse;
import com.repill.was.item.facade.FestivalFacade;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberNotFoundException;
import com.repill.was.member.query.MemberQueries;
import com.repill.was.review.controller.dto.response.ReviewDetailResponse;
import com.repill.was.review.controller.dto.response.ReviewListResponse;
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

@Api(tags = {SwaggerConfig.SwaggerTags.FESTIVAL})
@RestController
@FestivalV1Controller
@RequiredArgsConstructor
public class FestivalController {

    private final MemberQueries memberQueries;
    private final FestivalFacade festivalFacade;

    @ApiOperation("페스티벌 정보 호출")
    @GetMapping("/lists")
    public Page<FestivalInfoResponse> getLists(@AuthenticationPrincipal AccountId accountId,
                                               @RequestParam(required = false) String addressDetailA,
                                               @RequestParam(required = false) String addressDetailB,
                                               @RequestParam(required = false) LocalDateTime date,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        return festivalFacade.getLists(addressDetailA, addressDetailB, date, page, size);
    }

    @ApiOperation("페스티벌 정보 상세 보기")
    @GetMapping("/{id}")
    public FestivalInfoResponse getReviewDetail(@AuthenticationPrincipal AccountId accountId,
                                                @PathVariable("id") Long id,
                                                @RequestParam(required = false) String addressDetailA,
                                                @RequestParam(required = false) String addressDetailB,
                                                @RequestParam(required = false) LocalDateTime date) {
        memberQueries.findByAccountId(accountId).orElseThrow(() -> new BadRequestException("존재하지 않는 유저 입니다."));
        return festivalFacade.getDetail(id, addressDetailA, addressDetailB, date);
    }
}