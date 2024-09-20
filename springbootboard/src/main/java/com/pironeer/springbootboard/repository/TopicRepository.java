package com.pironeer.springbootboard.repository;

import com.pironeer.springbootboard.repository.domain.Comment;
import com.pironeer.springbootboard.repository.domain.Subcomment;
import com.pironeer.springbootboard.repository.domain.Topic;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TopicRepository {
    private final AtomicLong topicIdxGenerator = new AtomicLong(0);
    private final Map<Long, Topic> topicMap = new HashMap<>();
    private final AtomicLong commentIdxGenerator = new AtomicLong(0);
    private final Map<Long, Comment> commentMap = new HashMap<>();
    private final AtomicLong subcommentIdxGenerator = new AtomicLong(0);
    private final Map<Long, Subcomment> subcommentMap = new HashMap<>();

    public void save(Topic topic) {
        if(topic.getId() == null) {
            Long id = topicIdxGenerator.incrementAndGet();
            topic.setId(id);
            topicMap.put(id, topic);
        } else {
            topicMap.replace(topic.getId(), topic);
        }
    }

    public Optional<Topic> findById(Long id) {
        Assert.notNull(id, "Id must not be null");
        return Optional.ofNullable(topicMap.get(id));
    }

    public List<Topic> findAll() {
        return topicMap.values().stream().toList();
    }

    public void deleteById(Long id) {
        Assert.notNull(id, "Id must not be null");
        topicMap.remove(id);
    }

    // comment
    public void addCommentToTopic(Comment comment) {
        Topic topic = topicMap.get(comment.getTopicId());
        Assert.notNull(topic, "Topic not found");

        if(comment.getId() == null) {
            Long id = commentIdxGenerator.incrementAndGet();
            comment.setId(id);
            topic.addComment(comment);
            topicMap.replace(comment.getTopicId(), topic);
            commentMap.put(id, comment);
        } else {
            topicMap.replace(comment.getTopicId(), topic);
            commentMap.replace(comment.getId(), comment);
        }
    }

    public List<Comment> readAllComments(Long topicId) {
        return topicMap.get(topicId).getComments();
    }

    public Optional<Comment> findCommentById(Long id) {
        Assert.notNull(id, "Id must not be null");
        return Optional.ofNullable(commentMap.get(id));
    }

    public void deleteComment(Long topicId, Long commentId) {
        Assert.notNull(topicId, "TopicId must not be null");
        Topic topic = topicMap.get(topicId);

        Assert.notNull(commentId, "commentId must not be null");
        topic.deleteComment(commentId);
    }

    // Subcomment
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
