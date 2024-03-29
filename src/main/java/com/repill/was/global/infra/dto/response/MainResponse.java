package com.repill.was.global.infra.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainResponse {

    List<CategoryView> categoryList;


    public static MainResponse from(List<CategoryView> categoryView) {
        return new MainResponse(categoryView);
    }

    @Getter
    public static class CategoryView {
        private String mainName;
        private String subName;
        private Boolean isActive;

        public CategoryView(String mainName, String subName, Boolean isActive) {
            this.isActive = isActive;
            this.mainName = mainName;
            this.subName = subName;
        }
    }
}
