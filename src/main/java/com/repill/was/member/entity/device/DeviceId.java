package com.repill.was.member.entity.device;

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
public class DeviceId implements ValueObject<DeviceId> {

    private Long id;

    public DeviceId(Long id) {
        this.id = id;
    }

    static public List<DeviceId> from(List<Long> ids){
        return ids.stream().map(DeviceId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(DeviceId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}