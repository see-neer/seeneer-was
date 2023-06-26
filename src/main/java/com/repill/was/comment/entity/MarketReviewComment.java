package com.repill.was.comment.entity;

import com.repill.was.market.entity.MarketReviewId;
import com.repill.was.member.entity.MemberId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("MARKET")
public class MarketReviewComment extends Comment {

    @EmbeddedId
    MarketReviewCommentId id;

    public MarketReviewComment(MarketReviewCommentId id) {
        this.id = id;
    }
}
