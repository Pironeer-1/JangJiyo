package com.example.myconvention.board.controller;

import com.example.myconvention.global.dto.response.SuccessResponse;
import com.example.myconvention.global.dto.response.result.ListResult;
import com.example.myconvention.global.dto.response.result.SingleResult;
import com.example.myconvention.board.dto.request.BoardCreateRequest;
import com.example.myconvention.board.dto.request.BoardUpdateRequest;
import com.example.myconvention.board.dto.response.BoardResponse;
import com.example.myconvention.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "게시물(Topic)")
@RequestMapping("/api/topic")
public class BoardController {
    private final BoardService topicService;

    @PostMapping
    @Operation(summary = "게시물 작성")
    public SuccessResponse<SingleResult<Long>> create(
            @RequestAttribute("id") String userId,
            @Valid @RequestBody BoardCreateRequest request
    ) {
        System.out.println(userId);

        SingleResult<Long> save = topicService.save(request);
        return SuccessResponse.ok(save);
    }

    @GetMapping("/{topicId}")
    @Operation(summary = "게시물 단건 조회")
    public SuccessResponse<SingleResult<BoardResponse>> read(@PathVariable("topicId") Long id) {
        SingleResult<BoardResponse> result = topicService.findById(id);
        return SuccessResponse.ok(result);
    }

    @GetMapping
    @Operation(summary = "게시물 전체 조회")
    public SuccessResponse<ListResult<BoardResponse>> readAll() {
        ListResult<BoardResponse> result = topicService.findAll();
        return SuccessResponse.ok(result);
    }

    @PutMapping
    @Operation(summary = "게시물 수정")
    public SuccessResponse<SingleResult<BoardResponse>> update(@Valid @RequestBody BoardUpdateRequest request) {
        SingleResult<BoardResponse> response = topicService.update(request);
        return SuccessResponse.ok(response);
    }

    @DeleteMapping("/{topicId}")
    @Operation(summary = "게시물 삭제")
    public SuccessResponse<SingleResult<Long>> remove(@PathVariable("topicId") Long id) {
        SingleResult<Long> result = topicService.remove(id);
        return SuccessResponse.ok(result);
    }
}
