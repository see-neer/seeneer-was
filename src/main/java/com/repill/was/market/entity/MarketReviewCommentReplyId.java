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
public class MarketReviewCommentReplyId implements ValueObject<MarketReviewCommentReplyId> {

    private Long id;

    public MarketReviewCommentReplyId(Long id) {
        this.id = id;
    }

    static public List<MarketReviewCommentReplyId> from(List<Long> ids){
        return ids.stream().map(MarketReviewCommentReplyId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(MarketReviewCommentReplyId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarketReviewCommentReplyId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}