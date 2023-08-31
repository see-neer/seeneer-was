package com.repill.was.member.entity.memerreport;

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
public class MemberReportId implements ValueObject<MemberReportId> {

    private Long id;

    public MemberReportId(Long id) {
        this.id = id;
    }

    static public List<MemberReportId> from(List<Long> ids){
        return ids.stream().map(MemberReportId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(MemberReportId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberReportId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}