package com.repill.was.review.controller;

import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.enums.ItemType;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.member.MemberNotFoundException;
import com.repill.was.member.query.MemberQueries;
import com.repill.was.review.controller.dto.response.ReviewDetailResponse;
import com.repill.was.review.controller.dto.response.ReviewListResponse;
import com.repill.was.review.facade.ReviewFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Api(tags = {SwaggerConfig.SwaggerTags.REVIEW})
@ReviewV1Controller
@RequiredArgsConstructor
public class ReviewController {

    //todo redis cache로 변경
    private final ReviewFacade reviewFacade;
    private final MemberQueries memberQueries;

    @ApiOperation("리뷰 관리 호출")
    @GetMapping("/lists")
    public Page<ReviewListResponse> getLists(@AuthenticationPrincipal AccountId accountId,
                                             @RequestParam Long memberId,
                                             @RequestParam String type,
                                             @RequestParam int page,
                                             @RequestParam int size) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        return reviewFacade.getReviewLists(new MemberId(memberId), ItemType.valueOf(type), page, size);
    }

    @ApiOperation("리뷰 상세 보기")
    @GetMapping("/{id}")
    public ReviewDetailResponse getReviewDetail(@AuthenticationPrincipal AccountId accountId,
                                                                    @PathVariable("id") Long id) {
        memberQueries.findByAccountId(accountId).orElseThrow(() -> new BadRequestException("존재하지 않는 유저 입니다."));
        return reviewFacade.getReviewDetail(id);
    }
}
