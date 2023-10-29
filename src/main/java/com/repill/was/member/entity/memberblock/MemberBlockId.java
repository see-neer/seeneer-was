package com.repill.was.member.entity.memberblock;

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
public class MemberBlockId implements ValueObject<MemberBlockId> {

    private Long id;

    public MemberBlockId(Long id) {
        this.id = id;
    }

    static public List<MemberBlockId> from(List<Long> ids){
        return ids.stream().map(MemberBlockId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(MemberBlockId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberBlockId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}