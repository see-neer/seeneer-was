package com.repill.was.market.entity;

import com.repill.was.global.shard.ValueObject;
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
public class MarketReviewCommentId implements ValueObject<MarketReviewCommentId> {

    private Long id;

    public MarketReviewCommentId(Long id) {
        this.id = id;
    }

    static public List<MarketReviewCommentId> from(List<Long> ids){
        return ids.stream().map(MarketReviewCommentId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(MarketReviewCommentId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarketReviewCommentId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}