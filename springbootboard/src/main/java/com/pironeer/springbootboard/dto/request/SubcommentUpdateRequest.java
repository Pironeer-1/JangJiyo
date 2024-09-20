package com.pironeer.springbootboard.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record SubcommentUpdateRequest (
        @NotNull
        @Schema(description = "댓글 ID", example = "1")
        Long commentId,
        @Schema(description = "대댓글 ID", example = "1")
        Long id,
        @Schema(description = "수정할 대댓글 내용", example = "내용을 수정합니다")
        String content) {
}
