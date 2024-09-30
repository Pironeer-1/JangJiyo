package com.example.myconvention.global.dto.response.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseState {
    SUCCESS(1, "성공"),
    FAIL(-1, "실패");

    private int code;
    private String message;
}
