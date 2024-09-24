package com.pironeer.springbootboard.service;

import com.pironeer.springbootboard.dto.request.CommentCreateRequest;
import com.pironeer.springbootboard.dto.request.CommentUpdateRequest;
import com.pironeer.springbootboard.dto.response.CommentResponse;
import com.pironeer.springbootboard.repository.CommentRepository;
import com.pironeer.springbootboard.repository.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void addCommentToTopic(CommentCreateRequest request) {
        Comment comment = Comment.builder()
                .topicId(request.topicId())
                .content(request.content())
                .build();
        commentRepository.addCommentToTopic(comment);
    }

    public List<CommentResponse> readAllComments(Long topicId) {
        List<Comment> comments = commentRepository.readAllComments(topicId);
        return comments.stream().map(CommentResponse::of).toList();
    }

    public CommentResponse findCommentById(Long id) {
        Comment comment = commentRepository.findCommentById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return CommentResponse.of(comment);
    }

    public CommentResponse updateComment(CommentUpdateRequest request) {
        Comment comment = commentRepository.findCommentById(request.id())
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.addCommentToTopic(comment.update(request));
        return CommentResponse.of(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteComment(id);
    }
}
