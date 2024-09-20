package com.pironeer.springbootboard.repository;

import com.pironeer.springbootboard.repository.domain.Comment;
import com.pironeer.springbootboard.repository.domain.Subcomment;
import com.pironeer.springbootboard.repository.domain.Topic;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Getter
public class SubcommentRepository {
    private final AtomicLong subcommentIdxGenerator = new AtomicLong(0);
    private final Map<Long, Subcomment> subcommentMap = new HashMap<>();

    CommentRepository commentRepository = new CommentRepository();
    private Map<Long, Comment> commentMap = commentRepository.getCommentMap();

    public void addSubcommentToComment(Subcomment subcomment) {
        Comment comment = commentMap.get(subcomment.getCommentId());
        Assert.notNull(comment, "Comment not found");

        if(subcomment.getId() == null) {
            Long id = subcommentIdxGenerator.incrementAndGet();
            subcomment.setId(id);
            comment.addSubcomment(subcomment);
            commentMap.replace(subcomment.getCommentId(), comment);
            subcommentMap.put(id, subcomment);
        } else {
            commentMap.replace(subcomment.getCommentId(), comment);
            subcommentMap.replace(subcomment.getId(), subcomment);
        }
    }

    public List<Subcomment> readAllSubcomments(Long commentId) {
        return commentMap.get(commentId).getSubcomments();
    }

    public Optional<Subcomment> findSubcommentById(Long id) {
        Assert.notNull(id, "Id must not be null");
        return Optional.ofNullable(subcommentMap.get(id));
    }

    public void deleteSubcomment(Long id) {
        Assert.notNull(id, "Id must not be null");

        Comment comment = commentMap.get(subcommentMap.get(id).getCommentId());
        comment.deleteSubcomment(id);
        subcommentMap.remove(id);
    }
}
