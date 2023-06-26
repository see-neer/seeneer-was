package com.repill.was.comment.entity;

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
public class CommunityCommentId implements ValueObject<CommunityCommentId> {

    private Long id;

    public CommunityCommentId(Long id) {
        this.id = id;
    }

    static public List<CommunityCommentId> from(List<Long> ids){
        return ids.stream().map(CommunityCommentId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(CommunityCommentId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommunityCommentId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}