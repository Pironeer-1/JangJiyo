package com.example.myconvention.board.mapper;

import com.example.myconvention.board.dto.request.BoardCreateRequest;
import com.example.myconvention.board.entity.Board;

import java.time.LocalDateTime;

public class BoardMapper {
    public static Board from(BoardCreateRequest request) {
        LocalDateTime now = LocalDateTime.now();
        return Board.builder()
                .title(request.title())
                .content(request.content())
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
