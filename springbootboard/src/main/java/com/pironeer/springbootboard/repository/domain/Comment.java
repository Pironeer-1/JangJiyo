package com.pironeer.springbootboard.repository.domain;

import com.pironeer.springbootboard.dto.request.CommentUpdateRequest;
import lombok.Builder;
import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long topicId;
    private String content;

    @Builder
    public Comment(Long id, Long topicId, String content) {
        this.id = id;
        this.topicId = topicId;
        this.content = content;
    }

    public Comment update(CommentUpdateRequest request) {
        this.content = request.content();
        return this;
    }
}
