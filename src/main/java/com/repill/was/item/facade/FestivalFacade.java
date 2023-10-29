package com.repill.was.item.facade;

import com.repill.was.global.enums.Sort;
import com.repill.was.global.utils.PageUtils;
import com.repill.was.item.controller.dto.response.FestivalInfoResponse;
import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.query.FestivalQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FestivalFacade {

    private final FestivalQueries festivalQueries;

    public Page<FestivalInfoResponse> getLists(String addressDetailA, String addressDetailB, LocalDateTime date, int page, int size) {
        List<Festival> lists = festivalQueries.getLists(addressDetailA,
                addressDetailB,
                date,
                page,
                size);

        List<FestivalInfoResponse> collect = lists.stream().map(FestivalInfoResponse::from).collect(Collectors.toList());

        return PageUtils.makePage(collect, Sort.CREATED_AT.name(), size, page);
    }

    public FestivalInfoResponse getDetail(Long itemId, String addressDetailA, String addressDetailB, LocalDateTime date) {
        Festival detail = festivalQueries.getDetail(new FestivalId(itemId), addressDetailA, addressDetailB, date);
        return FestivalInfoResponse.from(detail);
    }
}
