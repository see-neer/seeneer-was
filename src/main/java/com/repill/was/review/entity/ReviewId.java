package com.repill.was.review.entity;

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
public class ReviewId implements ValueObject<ReviewId> {

    private Long id;

    public ReviewId(Long id) {
        this.id = id;
    }

    static public List<ReviewId> from(List<Long> ids){
        return ids.stream().map(ReviewId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(ReviewId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReviewId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}