package com.pironeer.springbootboard.dto.response;

import com.pironeer.springbootboard.repository.domain.Topic;

import java.time.LocalDateTime;

public record TopicResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public static TopicResponse of(Topic topic) {
        return new TopicResponse(
                topic.getId(),
                topic.getTitle(),
                topic.getContent(),
                topic.getCreatedAt(),
                topic.getUpdatedAt()
        );
    }

}
