package com.pironeer.springbootboard.repository.domain;

import com.pironeer.springbootboard.dto.request.TopicUpdateRequest;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Topic {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Comment> comments = new ArrayList<>();;

    @Builder
    public Topic(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.comments = comments != null ? comments : new ArrayList<>();
    }

    public Topic update(TopicUpdateRequest request) {
        this.title = request.title();
        this.content = request.content();
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void deleteComment(Long id) {
        this.comments.removeIf(comment -> comment.getId().equals(id));
    }
}
