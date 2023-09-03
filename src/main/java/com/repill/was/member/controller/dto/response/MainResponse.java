package com.repill.was.member.controller.dto.response;

import com.repill.was.member.controller.dto.view.MemberView;
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

        public CategoryView(String mainName, String subName) {
            this.mainName = mainName;
            this.subName = subName;
        }
    }
}
