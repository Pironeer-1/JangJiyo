package com.pironeer.springbootboard.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CommentCreateRequest(
    @NotNull
    @Schema(
            description = "게시물 ID",
            example = "1")
    Long topicId,
    @Schema(
            description = "댓글 내용",
            example = "내용입니다")
    String content) { }