package com.example.myconvention.topic.controller;

import com.example.myconvention.global.dto.response.SuccessResponse;
import com.example.myconvention.global.dto.response.result.ListResult;
import com.example.myconvention.global.dto.response.result.SingleResult;
import com.example.myconvention.topic.dto.request.TopicCreateRequest;
import com.example.myconvention.topic.dto.request.TopicUpdateRequest;
import com.example.myconvention.topic.dto.response.TopicResponse;
import com.example.myconvention.topic.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "게시물(Topic)")
@RequestMapping("/api/topic")
public class TopicController {
    private final TopicService topicService;

    @PostMapping
    @Operation(summary = "게시물 작성")
    public SuccessResponse<SingleResult<Long>> create(@Valid @RequestBody TopicCreateRequest request) {
        SingleResult<Long> save = topicService.save(request);
        return SuccessResponse.ok(save);
    }

    @GetMapping("/{topicId}")
    @Operation(summary = "게시물 단건 조회")
    public SuccessResponse<SingleResult<TopicResponse>> read(@PathVariable("topicId") Long id) {
        SingleResult<TopicResponse> result = topicService.findById(id);
        return SuccessResponse.ok(result);
    }

    @GetMapping
    @Operation(summary = "게시물 전체 조회")
    public SuccessResponse<ListResult<TopicResponse>> readAll() {
        ListResult<TopicResponse> result = topicService.findAll();
        return SuccessResponse.ok(result);
    }

    @PutMapping
    @Operation(summary = "게시물 수정")
    public SuccessResponse<SingleResult<TopicResponse>> update(@Valid @RequestBody TopicUpdateRequest request) {
        SingleResult<TopicResponse> response = topicService.update(request);
        return SuccessResponse.ok(response);
    }

    @DeleteMapping("/{topicId}")
    @Operation(summary = "게시물 삭제")
    public SuccessResponse<SingleResult<Long>> remove(@PathVariable("topicId") Long id) {
        SingleResult<Long> result = topicService.remove(id);
        return SuccessResponse.ok(result);
    }
}
