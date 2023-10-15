package com.repill.was.item.query;

import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.entity.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FestivalQueries {

    private final FestivalRepository festivalRepository;

    public Optional<Festival> findById(FestivalId festivalId) {
        return festivalRepository.findById(festivalId);
    }
}
