package com.example.myconvention.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(
    @NotBlank
    @Schema(description = "회원 ID", example = "id")
    String memberId,

    @NotBlank
    @Schema(description = "회원 비밀번호", example = "password")
    String password) {
}
