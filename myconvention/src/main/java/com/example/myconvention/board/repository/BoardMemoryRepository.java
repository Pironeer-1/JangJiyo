package com.example.myconvention.board.repository;

import com.example.myconvention.global.exception.CustomException;
import com.example.myconvention.global.exception.ErrorCode;
import com.example.myconvention.board.entity.Board;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BoardMemoryRepository implements BoardRepository {
    private final AtomicLong boardIdxGenerator = new AtomicLong(0);
    private final Map<Long, Board> boardMap = new HashMap<>();

    @Override
    public Board save(Board board) {
        if (board.getId() == null) {
            Long id = boardIdxGenerator.incrementAndGet();
            board.setId(id);
            boardMap.put(id, board);
            return board;
        } else {
            boardMap.replace(board.getId(), board);
            return board;
        }
    }

    @Override
    public Optional<Board> findById(Long id) {
        if (id == null) {
            throw new CustomException(ErrorCode.PARAMETER_NULL_ERROR);
        }
        return Optional.ofNullable(boardMap.get(id));
    }

    @Override
    public List<Board> findAll() {
        return boardMap.values().stream().toList();
    }

    @Override
    public Long deleteById(Long id) {
        if (id == null) {
            throw new CustomException(ErrorCode.PARAMETER_NULL_ERROR);
        }
        boardMap.remove(id);
        return id;
    }
}
