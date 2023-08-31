package com.repill.was.member.entity.memberfavoritemarket;

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
public class MemberFavoriteMarketId  implements ValueObject<MemberFavoriteMarketId> {

    private Long id;

    public MemberFavoriteMarketId(Long id) {
        this.id = id;
    }

    static public List<MemberFavoriteMarketId> from(List<Long> ids){
        return ids.stream().map(MemberFavoriteMarketId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(MemberFavoriteMarketId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberFavoriteMarketId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
