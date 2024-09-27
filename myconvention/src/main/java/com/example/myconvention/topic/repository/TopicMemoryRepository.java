package com.example.myconvention.topic.repository;

import com.example.myconvention.global.exception.CustomException;
import com.example.myconvention.topic.entity.Topic;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TopicMemoryRepository implements TopicRepository {
    private final AtomicLong topicIdxGenerator = new AtomicLong(0);
    private final Map<Long, Topic> topicMap = new HashMap<>();

    @Override
    public Topic save(Topic topic) {
        if (topic.getId() == null) {
            Long id = topicIdxGenerator.incrementAndGet();
            topic.setId(id);
            topicMap.put(id, topic);
            return topic;
        } else {
            topicMap.replace(topic.getId(), topic);
            return topic;
        }
    }

    @Override
    public Optional<Topic> findById(Long id) {
        Assert.notNull(id, "Id must not be Null");
        return Optional.ofNullable(topicMap.get(id));
    }

    @Override
    public List<Topic> findAll() {
        return topicMap.values().stream().toList();
    }

    @Override
    public Long deleteById(Long id) {
        Assert.notNull(id, "Id must not be Null");
        topicMap.remove(id);
        return id;
    }
}
