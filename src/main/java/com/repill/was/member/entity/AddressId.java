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
public class AddressId implements ValueObject<AddressId> {

    private Long id;

    public AddressId(Long id) {
        this.id = id;
    }

    static public List<AddressId> from(List<Long> ids){
        return ids.stream().map(AddressId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(AddressId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}