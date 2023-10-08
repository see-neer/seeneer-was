package com.repill.was.member.entity.memberLike;

import com.repill.was.global.model.ValueObject;
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
public class MemberLikeId implements ValueObject<MemberLikeId> {

    private Long id;

    public MemberLikeId(Long id) {
        this.id = id;
    }

    static public List<MemberLikeId> from(List<Long> ids){
        return ids.stream().map(MemberLikeId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(MemberLikeId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberLikeId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}