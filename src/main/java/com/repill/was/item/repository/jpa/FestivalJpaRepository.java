package com.repill.was.item.repository.jpa;

import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.FestivalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalJpaRepository extends JpaRepository<Festival, FestivalId> {
}
