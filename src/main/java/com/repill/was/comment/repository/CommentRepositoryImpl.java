package com.repill.was.comment.repository;

import com.repill.was.comment.entity.Comment;
import com.repill.was.comment.entity.CommentId;
import com.repill.was.comment.entity.CommentRepository;
import com.repill.was.comment.repository.jpa.CommentJpaRepository;
import com.repill.was.global.sequencegenerator.SequenceGenerator;
import com.repill.was.member.entity.member.MemberId;
import com.repill.was.member.entity.memberLike.MemberLike;
import com.repill.was.member.entity.memberLike.MemberLikeId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.repill.was.member.entity.memberLike.QMemberLike.memberLike;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final SequenceGenerator sequenceGenerator;
    private final CommentJpaRepository commentJpaRepository;

    @Override
    public CommentId nextId() {
        return new CommentId(sequenceGenerator.generate());
    }

    @Override
    public Comment save(Comment comment) {
        return commentJpaRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentJpaRepository.delete(comment);
    }

    @Override
    public Optional<Comment> findById(CommentId commentId) {
        return commentJpaRepository.findById(commentId);
    }
}
