package com.repill.was.member.entity.favoriteitems;

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
public class FavoriteItemId implements ValueObject<FavoriteItemId> {

    private Long id;

    public FavoriteItemId(Long id) {
        this.id = id;
    }

    static public List<FavoriteItemId> from(List<Long> ids){
        return ids.stream().map(FavoriteItemId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(FavoriteItemId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FavoriteItemId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}