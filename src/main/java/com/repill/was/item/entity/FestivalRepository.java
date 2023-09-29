package com.repill.was.item.entity;

import java.util.Optional;

public interface FestivalRepository {
    FestivalId nextId();

    Festival save(Festival market);

    Optional<Festival> findById(FestivalId festivalId);
}
