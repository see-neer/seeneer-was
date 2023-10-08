package com.repill.was.comment.repository.jpa;

import com.repill.was.comment.entity.Comment;
import com.repill.was.comment.entity.CommentId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, CommentId> {

}
