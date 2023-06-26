package com.repill.was.member.entity;

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
public class MemberFollowerId implements ValueObject<MemberFollowerId> {

    private Long id;

    public MemberFollowerId(Long id) {
        this.id = id;
    }

    static public List<MemberFollowerId> from(List<Long> ids){
        return ids.stream().map(MemberFollowerId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(MemberFollowerId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberFollowerId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}