package com.repill.was.market.entity;

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
public class MarketId implements ValueObject<MarketId> {

    private Long id;

    public MarketId(Long id) {
        this.id = id;
    }

    static public List<MarketId> from(List<Long> ids){
        return ids.stream().map(MarketId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(MarketId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarketId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}