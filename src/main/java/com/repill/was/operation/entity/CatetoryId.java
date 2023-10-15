package com.repill.was.operation.entity;

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
public class CatetoryId implements ValueObject<CatetoryId> {

    private Long id;

    public CatetoryId(Long id) {
        this.id = id;
    }

    static public List<CatetoryId> from(List<Long> ids){
        return ids.stream().map(CatetoryId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(CatetoryId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CatetoryId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}