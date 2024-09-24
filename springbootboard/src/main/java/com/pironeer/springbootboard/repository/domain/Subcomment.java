package com.pironeer.springbootboard.repository.domain;

import com.pironeer.springbootboard.dto.request.SubcommentUpdateRequest;
import lombok.Builder;
import lombok.Data;

@Data
public class Subcomment {
    private Long id;
    private Long commentId;
    private String content;

    @Builder
    public Subcomment(Long id, Long commentId, String content) {
        this.id = id;
        this.commentId = commentId;
        this.content = content;
    }

    public Subcomment update(SubcommentUpdateRequest request) {
        this.content = request.content();
        return this;
    }
}
