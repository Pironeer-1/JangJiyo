package com.pironeer.springbootboard.repository.domain;

import com.pironeer.springbootboard.dto.request.CommentUpdateRequest;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Comment {
    private Long id;
    private Long topicId;
    private String content;
    private List<Subcomment> subcomments  = new ArrayList<>();;

    @Builder
    public Comment(Long id, Long topicId, String content, List<Subcomment> subcomments) {
        this.id = id;
        this.topicId = topicId;
        this.content = content;
        this.subcomments = subcomments != null ? subcomments : new ArrayList<>();
    }

    public Comment update(CommentUpdateRequest request) {
        this.content = request.content();
        return this;
    }

    public void addSubcomment(Subcomment subcomment) {
        this.subcomments.add(subcomment);
    }

    public void deleteSubcomment(Long id) {
        this.subcomments.removeIf(subcomment -> subcomment.getId().equals(id));
    }
}
