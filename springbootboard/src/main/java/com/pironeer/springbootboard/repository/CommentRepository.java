package com.pironeer.springbootboard.repository;

import com.pironeer.springbootboard.repository.domain.Comment;
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
public class CommentRepository {
    private final AtomicLong commentIdxGenerator = new AtomicLong(0);
    private static final Map<Long, Comment> commentMap = new HashMap<>();

    public static Map<Long, Comment> getCommentMap() {
        return commentMap;
    }

    TopicRepository topicRepository = new TopicRepository();
    private Map<Long, Topic> topicMap = topicRepository.getTopicMap();

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

    public void deleteComment(Long id) {
        Assert.notNull(id, "Id must not be null");

        Topic topic = topicMap.get(commentMap.get(id).getTopicId());
        topic.deleteComment(id);
        commentMap.remove(id);
    }
}
