package com.repill.was.review.controller;

import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.global.enums.ItemType;
import com.repill.was.global.response.CommonResponse;
import com.repill.was.member.entity.account.AccountId;
import com.repill.was.member.entity.member.Member;
import com.repill.was.member.entity.member.MemberNotFoundException;
import com.repill.was.member.query.MemberQueries;
import com.repill.was.review.controller.dto.response.ReviewDetailResponse;
import com.repill.was.review.controller.dto.response.ReviewListResponse;
import com.repill.was.review.facade.ReviewFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    public CommonResponse<List<ReviewListResponse>> getLists(@AuthenticationPrincipal AccountId accountId,
                                                       @RequestParam String type,
                                                       @RequestParam(required = false) Long cursorId,
                                                       @RequestParam int size) {
        Member member = memberQueries.findByAccountId(accountId).orElseThrow(MemberNotFoundException::new);
        List<ReviewListResponse> lists = reviewFacade.getReviewLists(member.getId(), ItemType.valueOf(type), cursorId, size);
        return CommonResponse.success(lists);
    }

    @ApiOperation("리뷰 상세 보기")
    @GetMapping("/{id}")
    public CommonResponse<ReviewDetailResponse> getReviewDetail(@AuthenticationPrincipal AccountId accountId,
                                                                    @PathVariable("id") Long id,
                                                                    @RequestParam Long itemId,
                                                                    @RequestParam String itemType) {
        memberQueries.findByAccountId(accountId).orElseThrow(() -> new BadRequestException("존재하지 않는 유저 입니다."));
        ReviewDetailResponse reviewDetail = reviewFacade.getReviewDetail(id, itemId, ItemType.valueOf(itemType));
        return CommonResponse.success(reviewDetail);
    }
}
