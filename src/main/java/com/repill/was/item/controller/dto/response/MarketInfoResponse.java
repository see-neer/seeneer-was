package com.repill.was.item.controller.dto.response;

import com.repill.was.global.utils.TimeUtils;
import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.Market;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MarketInfoResponse {

    private Long itemId;
    private List<String> imageSrc;
    private String name;
    private String addressDetailA;
    private String addressDetailB;
    private String date;
    private int score;
    private int totalComment;

    public MarketInfoResponse(Long itemId, List<String> imageSrc, String name, String addressDetailA, String addressDetailB, String date, int score, int totalComment) {
        this.itemId = itemId;
        this.imageSrc = imageSrc;
        this.name = name;
        this.addressDetailA = addressDetailA;
        this.addressDetailB = addressDetailB;
        this.date = date;
        this.score = score;
        this.totalComment = totalComment;
    }

    public static MarketInfoResponse from(Market market) {
        return new MarketInfoResponse(
                market.getId().getId(),
                market.getImages().getImages(),
                market.getName(),
                market.getAddressDetailA(),
                market.getAddressDetailB(),
                market.getDate(),
                1,
                2
        );
    }
}
