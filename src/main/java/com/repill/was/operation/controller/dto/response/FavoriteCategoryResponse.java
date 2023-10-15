package com.repill.was.operation.controller.dto.response;

import com.repill.was.global.enums.ClosingAccountReason;
import com.repill.was.global.enums.FavoriteCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Getter
@NoArgsConstructor
public class FavoriteCategoryResponse {
    @ApiModelProperty(required = true, notes = "사유 code")
    private String code;
    @ApiModelProperty(required = true, notes = "사유 표기용 text")
    private String displayText;
    @Deprecated
    @ApiModelProperty(required = true, notes = "Deprecated 사유에 해당하는 탈퇴 방지 제안")
    private String suggestion;

    public FavoriteCategoryResponse(String code, String displayText, String suggestion) {
        this.code = code;
        this.displayText = displayText;
        this.suggestion = suggestion;
    }

    public static FavoriteCategoryResponse from(FavoriteCategory category, Locale locale) {
        return new FavoriteCategoryResponse(
                category.name(),
                category.getDisplayText(locale),
                category.getSuggestion(locale)
        );
    }
}