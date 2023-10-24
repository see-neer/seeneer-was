package com.repill.was.comment.entity;

import com.repill.was.global.exception.BadRequestException;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Comment {

    @Id @GeneratedValue
    private CommentId id;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "postMemberId", nullable = false))
    MemberId postMemberId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "commentMemberId", nullable = false))
    MemberId commentMemberId;

    private int parentOrderId;

    // 셀프조인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_id")
    private Comment parents;

    @OneToMany(mappedBy = "parents", cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> child = new ArrayList<>();

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Column(columnDefinition = "BIGINT(20)", nullable = false)
    private Long likeCount;

    @Column(columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)", nullable = false)
    private ZonedDateTime createdAt;

    @Column(columnDefinition = "DATETIME(3)")
    private ZonedDateTime updatedAt;

    public void addLike(MemberLike memberLike) {
        if(!memberLike.getItemId().equals(this.id.getId())) throw new BadRequestException("좋아요 실패");
        this.likeCount += 1;
    }

    public void deleteLike(MemberLike memberLike) {
        if(!memberLike.getItemId().equals(this.id.getId())) throw new BadRequestException("좋아요 취소 실패");
        this.likeCount -= 1;
    }
}
