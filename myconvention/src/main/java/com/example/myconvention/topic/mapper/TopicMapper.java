package com.example.myconvention.topic.mapper;

import com.example.myconvention.topic.dto.request.TopicCreateRequest;
import com.example.myconvention.topic.entity.Topic;

import java.time.LocalDateTime;

public class TopicMapper {
    public static Topic from(TopicCreateRequest request) {
        LocalDateTime now = LocalDateTime.now();
        return Topic.builder()
                .title(request.title())
                .content(request.content())
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
