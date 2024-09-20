package com.pironeer.springbootboard.controller;

import com.pironeer.springbootboard.dto.request.*;
import com.pironeer.springbootboard.dto.response.CommentResponse;
import com.pironeer.springbootboard.dto.response.SubcommentResponse;
import com.pironeer.springbootboard.dto.response.TopicResponse;
import com.pironeer.springbootboard.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "게시물(Topic)")
@RequestMapping("/api/topic")
public class TopicController {
    private final TopicService topicService;

    @PostMapping
    @Operation(summary = "게시물 작성")
    public ResponseEntity<?> create(@Valid @RequestBody TopicCreateRequest request) {
        topicService.save(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{topicId}")
    @Operation(summary = "게시물 단건 조회")
    public ResponseEntity<TopicResponse> read(@PathVariable("topicId") Long id) {
        TopicResponse response = topicService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    @Operation(summary = "게시물 전체 조회")
    public ResponseEntity<List<TopicResponse>> readAll() {
        List<TopicResponse> responses = topicService.findAll();
        return ResponseEntity.ok().body(responses);
    }

    @PutMapping
    @Operation(summary = "게시물 수정")
    public ResponseEntity<TopicResponse> update(@Valid @RequestBody TopicUpdateRequest request) {
        TopicResponse response = topicService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{topicId}")
    @Operation(summary = "게시물 삭제")
    public ResponseEntity<?> delete(@PathVariable("topicId") Long id) {
        topicService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Comment
    @PostMapping("/{topicId}/comment")
    @Operation(summary = "댓글 작성")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentCreateRequest request) {
        topicService.addCommentToTopic(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{topicId}/comment")
    @Operation(summary = "댓글 전체 조회")
    public ResponseEntity<List<CommentResponse>> readAllComments(@PathVariable("topicId") Long topicId) {
        List<CommentResponse> responses = topicService.readAllComments(topicId);
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/{topicId}/comment/{commentId}")
    @Operation(summary = "댓글 단건 조회")
    public ResponseEntity<CommentResponse> readCommentById(@PathVariable("topicId") Long topicId, @PathVariable("commentId") Long commentId) {
        CommentResponse response = topicService.readCommentById(topicId, commentId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{topicId}/comment")
    @Operation(summary = "댓글 수정")
    public ResponseEntity<CommentResponse> updateComment(@Valid @RequestBody CommentUpdateRequest request) {
        CommentResponse response = topicService.updateComment(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{topicId}/comment/{commentId}")
    @Operation(summary = "댓글 삭제")
    public ResponseEntity<?> deleteComment(@PathVariable("topicId") Long topicId, @PathVariable("commentId") Long commentId) {
        topicService.deleteComment(topicId, commentId);
        return ResponseEntity.ok().build();
    }

    // SubComment
    @PostMapping("/{topicId}/comment/{commentId}/subcomment")
    @Operation(summary = "대댓글 작성")
    public ResponseEntity<?> createSubcomment(@Valid @RequestBody SubcommentCreateRequest request) {
        topicService.addSubcommentToComment(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{topicId}/comment/{commentId}/subcomment")
    @Operation(summary = "대댓글 전체 조회")
    public ResponseEntity<List<SubcommentResponse>> readAllSubcomments(@PathVariable("commentId") Long commentId) {
        List<SubcommentResponse> responses = topicService.readAllSubcomments(commentId);
        return ResponseEntity.ok().body(responses);
    }
}
