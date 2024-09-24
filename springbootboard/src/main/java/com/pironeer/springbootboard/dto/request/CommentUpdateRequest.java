package com.pironeer.springbootboard.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CommentUpdateRequest (
    @NotNull
    @Schema(description = "게시물 ID", example = "1")
    Long topicId,
    @Schema(description = "댓글 ID", example = "1")
    Long id,
    @Schema(description = "수정할 댓글 내용", example = "내용을 수정합니다")
    String content) {
}
