package com.repill.was.item.entity;

import com.repill.was.item.controller.dto.response.FestivalInfoResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FestivalRepository {
    FestivalId nextId();

    Festival save(Festival market);

    Optional<Festival> findById(FestivalId festivalId);

    List<Festival> getLists(String addressDetailA, String addressDetailB, LocalDateTime date);

    Festival getDetail(FestivalId festivalId, String addressDetailA, String addressDetailB, LocalDateTime date);
}
