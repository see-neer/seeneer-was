package com.repill.was.item.facade;

import com.repill.was.global.enums.Sort;
import com.repill.was.global.utils.PageUtils;
import com.repill.was.item.controller.dto.response.FestivalInfoResponse;
import com.repill.was.item.controller.dto.response.MarketInfoResponse;
import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.entity.Market;
import com.repill.was.item.entity.MarketId;
import com.repill.was.item.query.MarketQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketFacade {

    private final MarketQueries marketQueries;

    public Page<MarketInfoResponse> getLists(String addressDetailA, String addressDetailB, String date, int page, int size) {
        List<Market> lists = marketQueries.getLists(addressDetailA,
                addressDetailB,
                date,
                page,
                size);

        List<MarketInfoResponse> collect = lists.stream().map(MarketInfoResponse::from).collect(Collectors.toList());

        return PageUtils.makePage(collect, Sort.CREATED_AT.name(), size, page);
    }

    public MarketInfoResponse getDetail(Long itemId, String addressDetailA, String addressDetailB, String date) {
        Market detail = marketQueries.getDetail(new MarketId(itemId), addressDetailA, addressDetailB, date);
        return MarketInfoResponse.from(detail);
    }
}
