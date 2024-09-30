package com.example.myconvention.board.repository;

import com.example.myconvention.board.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board save(Board topic);

    Optional<Board> findById(Long id);

    List<Board> findAll();

    Long deleteById(Long id);
}
