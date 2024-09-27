package com.example.myconvention.topic.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TopicCreateRequest(
    @NotBlank
    @Schema(description = "게시물 제목", example = "제목")
    String title,
    @Schema(description = "게시물 내용", example = "내용")
    String content) {
}
