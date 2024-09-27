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
    private final BoardRepository topicRepository;

    public SingleResult<Long> save(BoardCreateRequest request) {
        Board savedTopic = topicRepository.save(BoardMapper.from(request));
        return ResponseService.getSingleResult(savedTopic.getId());
    }

    public SingleResult<BoardResponse> findById(Long id) {
        Board topic = topicRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        BoardResponse topicResponse = BoardResponse.of(topic);
        return ResponseService.getSingleResult(topicResponse);
    }

    public ListResult<BoardResponse> findAll() {
        List<Board> topics = topicRepository.findAll();
        List<BoardResponse> list = topics.stream().map(BoardResponse::of).toList();
        return ResponseService.getListResult(list);
    }

    public SingleResult<BoardResponse> update(BoardUpdateRequest request) {
        Board topic = topicRepository.findById(request.id())
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        topicRepository.save(topic.update(request));
        BoardResponse topicResponse = BoardResponse.of(topic);
        return ResponseService.getSingleResult(topicResponse);
    }

    public SingleResult<Long> remove(Long id) {
        Long deletedId = topicRepository.deleteById(id);
        return ResponseService.getSingleResult(deletedId);
    }
}
