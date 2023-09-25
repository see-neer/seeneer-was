package com.repill.was.festival.entity;

import com.repill.was.global.shard.model.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class FestivalId implements ValueObject<FestivalId> {

    private Long id;

    public FestivalId(Long id) {
        this.id = id;
    }

    static public List<FestivalId> from(List<Long> ids){
        return ids.stream().map(FestivalId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(FestivalId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FestivalId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}