package com.pironeer.springbootboard.repository;

import com.pironeer.springbootboard.repository.domain.Comment;
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

    public void addCommentToTopic(Comment comment) {
        Topic topic = topicMap.get(comment.getTopicId());
        Assert.notNull(topic, "Topic not found");

        comment.setId(commentIdxGenerator.incrementAndGet());
        topic.addComment(comment);

        topicMap.replace(comment.getTopicId(), topic);
    }

    public List<Comment> readAllComments(Long topicId) {
        return topicMap.get(topicId).getComments();
    }

    public Comment readCommentById(Long topicId, Long commentId) {
        Assert.notNull(topicId, "Id must not be null");
        Topic topic = topicMap.get(topicId);

        return topic.getComments().stream().filter(comment -> comment.getId().equals(commentId)).findFirst()
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }
}
