package com.pironeer.springbootboard.repository;

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
}
