package com.repill.was.member.entity.account;

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
public class AccountId implements ValueObject<AccountId> {

    private Long id;

    public AccountId(Long id) {
        this.id = id;
    }

    static public List<AccountId> from(List<Long> ids){
        return ids.stream().map(AccountId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(AccountId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}