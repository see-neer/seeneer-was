package com.repill.was.item.query;

import com.repill.was.item.controller.dto.response.FestivalInfoResponse;
import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.entity.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FestivalQueries {

    private final FestivalRepository festivalRepository;

    public Optional<Festival> findById(FestivalId festivalId) {
        return festivalRepository.findById(festivalId);
    }

    public List<Festival> getLists(String addressDetailA, String addressDetailB, LocalDateTime date, int page, int size) {
        return festivalRepository.getLists(addressDetailA, addressDetailB, date);
    }

    public Festival getDetail(FestivalId festivalId, String addressDetailA, String addressDetailB, LocalDateTime date) {
        return festivalRepository.getDetail(festivalId, addressDetailA, addressDetailB, date);
    }
}
