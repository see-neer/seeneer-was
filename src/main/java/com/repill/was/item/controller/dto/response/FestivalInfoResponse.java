package com.repill.was.item.controller.dto.response;

import com.repill.was.global.utils.TimeUtils;
import com.repill.was.item.entity.Festival;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FestivalInfoResponse {

    private Long itemId;
    private List<String> imageSrc;
    private String name;
    private String addressDetailA;
    private String addressDetailB;
    private String date;
    private int score;
    private int totalComment;

    public FestivalInfoResponse(Long itemId, List<String> imageSrc, String name, String addressDetailA, String addressDetailB, String date, int score, int totalComment) {
        this.itemId = itemId;
        this.imageSrc = imageSrc;
        this.name = name;
        this.addressDetailA = addressDetailA;
        this.addressDetailB = addressDetailB;
        this.date = date;
        this.score = score;
        this.totalComment = totalComment;
    }

    public static FestivalInfoResponse from(Festival festival) {
        return new FestivalInfoResponse(
                festival.getId().getId(),
                festival.getImages().getImages(),
                festival.getName(),
                festival.getAddressDetailA(),
                festival.getAddressDetailB(),
                TimeUtils.convertToISO_8061(festival.getDate()),
                1,
                2
        );
    }
}
