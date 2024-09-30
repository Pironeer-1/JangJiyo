package com.example.myconvention.board.service;

import com.example.myconvention.global.dto.response.result.ListResult;
import com.example.myconvention.global.dto.response.result.SingleResult;
import com.example.myconvention.global.exception.CustomException;
import com.example.myconvention.global.exception.ErrorCode;
import com.example.myconvention.global.service.ResponseService;
import com.example.myconvention.board.dto.request.BoardCreateRequest;
import com.example.myconvention.board.dto.request.BoardUpdateRequest;
import com.example.myconvention.board.dto.response.BoardResponse;
import com.example.myconvention.board.entity.Board;
import com.example.myconvention.board.mapper.BoardMapper;
import com.example.myconvention.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public SingleResult<Long> save(BoardCreateRequest request) {
        Board savedBoard = boardRepository.save(BoardMapper.from(request));
        return ResponseService.getSingleResult(savedBoard.getId());
    }

    public SingleResult<BoardResponse> findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        BoardResponse boardResponse = BoardResponse.of(board);
        return ResponseService.getSingleResult(boardResponse);
    }

    public ListResult<BoardResponse> findAll() {
        List<Board> boards = boardRepository.findAll();
        List<BoardResponse> list = boards.stream().map(BoardResponse::of).toList();
        return ResponseService.getListResult(list);
    }

    public SingleResult<BoardResponse> update(BoardUpdateRequest request) {
        Board board = boardRepository.findById(request.id())
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        boardRepository.save(board.update(request));
        BoardResponse boardResponse = BoardResponse.of(board);
        return ResponseService.getSingleResult(boardResponse);
    }

    public SingleResult<Long> remove(Long id) {
        Long deletedId = boardRepository.deleteById(id);
        return ResponseService.getSingleResult(deletedId);
    }
}
