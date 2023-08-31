package com.repill.was.member.entity.recentlyviewditem;

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
public class RecentlyViewedItemId implements ValueObject<RecentlyViewedItemId> {

    private Long id;

    public RecentlyViewedItemId(Long id) {
        this.id = id;
    }

    static public List<RecentlyViewedItemId> from(List<Long> ids){
        return ids.stream().map(RecentlyViewedItemId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(RecentlyViewedItemId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecentlyViewedItemId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}