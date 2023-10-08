package com.repill.was.comment.entity;

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
public class CommentId implements ValueObject<CommentId> {

    private Long id;

    public CommentId(Long id) {
        this.id = id;
    }

    static public List<CommentId> from(List<Long> ids){
        return ids.stream().map(CommentId::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean sameValueAs(CommentId o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentId oId = o;
        return Objects.equals(id, oId.id);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}