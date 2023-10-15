package com.repill.was.item.query.vo;

import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.Market;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVO {

    private Long id;
    private List<String> imageSrc;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public static ItemVO from (Festival festival) {
        return new ItemVO(
                festival.getId().getId(),
                festival.getImages().getImages(),
                festival.getName(),
                festival.getCreatedAt(),
                festival.getUpdatedAt()
        );
    }

    public static ItemVO from (Market market) {
        return new ItemVO(
                market.getId().getId(),
                market.getImages().getImages(),
                market.getName(),
                market.getCreatedAt(),
                market.getUpdatedAt()
        );
    }
}
