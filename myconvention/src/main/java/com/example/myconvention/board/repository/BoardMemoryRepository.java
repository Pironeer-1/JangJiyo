package com.example.myconvention.board.repository;

import com.example.myconvention.global.exception.CustomException;
import com.example.myconvention.global.exception.ErrorCode;
import com.example.myconvention.board.entity.Board;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BoardMemoryRepository implements BoardRepository {
    private final AtomicLong topicIdxGenerator = new AtomicLong(0);
    private final Map<Long, Board> topicMap = new HashMap<>();

    @Override
    public Board save(Board topic) {
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
    public Optional<Board> findById(Long id) {
        if (id == null) {
            throw new CustomException(ErrorCode.PARAMETER_NULL_ERROR);
        }
        return Optional.ofNullable(topicMap.get(id));
    }

    @Override
    public List<Board> findAll() {
        return topicMap.values().stream().toList();
    }

    @Override
    public Long deleteById(Long id) {
        Assert.notNull(id, "Id must not be Null");
        topicMap.remove(id);
        return id;
    }
}
