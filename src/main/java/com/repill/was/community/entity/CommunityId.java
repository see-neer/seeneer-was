package com.repill.was.community.entity;

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
public class CommunityId implements ValueObject<CommunityId> {

    private Long id;

    public CommunityId(Long id) {
        this.id = id;
    }

    static public List<CommunityId> from(List<Long> ids){
        return ids.stream().map(CommunityId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(CommunityId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommunityId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}