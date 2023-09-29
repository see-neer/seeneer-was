package com.repill.was.item.repository;




import com.repill.was.item.entity.Festival;
import com.repill.was.item.entity.FestivalId;
import com.repill.was.item.entity.FestivalRepository;
import com.repill.was.item.repository.jpa.FestivalJpaRepository;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FestivalRepositoryImpl implements FestivalRepository {

    private final SequenceGenerator sequenceGenerator;
    private final FestivalJpaRepository festivalJpaRepository;

    @Override
    public FestivalId nextId() {
        return new FestivalId(sequenceGenerator.generate());
    }

    @Override
    public Festival save(Festival festival) {
        return festivalJpaRepository.save(festival);
    }

    @Override
    public Optional<Festival> findById(FestivalId festivalId) {
        return festivalJpaRepository.findById(festivalId);
    }
}
