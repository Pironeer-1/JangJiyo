package com.pironeer.springbootboard.dto.response;

import com.pironeer.springbootboard.repository.domain.Subcomment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record SubcommentResponse(
        @Schema(description = "대댓글 ID", example = "1")
        Long id,
        @Schema(description = "댓글 ID", example = "1")
        Long commentId,
        @Schema(description = "대댓글 내용", example = "내용입니다")
        String content) {
    public static SubcommentResponse of(Subcomment subcomment) {
        return SubcommentResponse.builder()
                .id(subcomment.getId())
                .commentId(subcomment.getCommentId())
                .content(subcomment.getContent())
                .build();
    }
}