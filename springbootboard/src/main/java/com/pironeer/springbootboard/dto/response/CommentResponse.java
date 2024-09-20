package com.pironeer.springbootboard.dto.response;

import com.pironeer.springbootboard.repository.domain.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CommentResponse(
        @Schema(description = "게시물 ID", example = "1")
        Long topicId,
        @Schema(description = "댓글 ID", example = "1")
        Long id,
        @Schema(description = "댓글 내용", example = "내용입니다")
        String content) {
    public static CommentResponse of(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .topicId(comment.getTopicId())
                .content(comment.getContent())
                .build();
    }
}
