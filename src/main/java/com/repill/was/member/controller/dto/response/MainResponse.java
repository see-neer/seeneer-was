package com.repill.was.member.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainResponse {

    String nickName;
    List<String> categoryList;


    public static MainResponse from(String nickName, List<String> categoryList) {
        return new MainResponse(nickName, categoryList);
    }
}
