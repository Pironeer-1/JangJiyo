package com.pironeer.springbootboard.service;

import com.pironeer.springbootboard.dto.request.*;
import com.pironeer.springbootboard.dto.response.CommentResponse;
import com.pironeer.springbootboard.dto.response.SubcommentResponse;
import com.pironeer.springbootboard.dto.response.TopicResponse;
import com.pironeer.springbootboard.repository.TopicRepository;
import com.pironeer.springbootboard.repository.domain.Comment;
import com.pironeer.springbootboard.repository.domain.Subcomment;
import com.pironeer.springbootboard.repository.domain.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    public void save(TopicCreateRequest request) {
        Topic topic = Topic.builder()
                .title(request.title())
                .content(request.content())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        topicRepository.save(topic);
    }

    public TopicResponse findById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));
        return TopicResponse.of(topic);
    }

    public List<TopicResponse> findAll() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream().map(TopicResponse::of).toList();
    }

    public TopicResponse update(TopicUpdateRequest request) {
        Topic topic = topicRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Topic not found"));
        topicRepository.save(topic.update(request));
        return TopicResponse.of(topic);
    }

    public void deleteById(Long id) {
        topicRepository.deleteById(id);
    }

    // Comment
    public void addCommentToTopic(CommentCreateRequest request) {
        Comment comment = Comment.builder()
                .topicId(request.topicId())
                .content(request.content())
                .build();
        topicRepository.addCommentToTopic(comment);
    }

    public List<CommentResponse> readAllComments(Long topicId) {
        List<Comment> comments = topicRepository.readAllComments(topicId);
        return comments.stream().map(CommentResponse::of).toList();
    }

    public CommentResponse findCommentById(Long id) {
        Comment comment = topicRepository.findCommentById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return CommentResponse.of(comment);
    }

    public CommentResponse updateComment(CommentUpdateRequest request) {
        Comment comment = topicRepository.findCommentById(request.id())
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        topicRepository.addCommentToTopic(comment.update(request));
        return CommentResponse.of(comment);
    }

    public void deleteComment(Long topicId, Long commentId) {
        topicRepository.deleteComment(topicId, commentId);
    }

    // Subcomment
    public void addSubcommentToComment(SubcommentCreateRequest request) {
        Subcomment subcomment = Subcomment.builder()
                .commentId(request.commentId())
                .content(request.content())
                .build();
        topicRepository.addSubcommentToComment(subcomment);
    }

    public List<SubcommentResponse> readAllSubcomments(Long commentId) {
        List<Subcomment> subcomments = topicRepository.readAllSubcomments(commentId);
        return subcomments.stream().map(SubcommentResponse::of).toList();
    }

    public SubcommentResponse findSubcommentById(Long id) {
        Subcomment subcomment = topicRepository.findSubcommentById(id)
                .orElseThrow(() -> new RuntimeException("Subcomment not found"));
        return SubcommentResponse.of(subcomment);
    }

    public SubcommentResponse updateSubcomment(SubcommentUpdateRequest request) {
        Subcomment subcomment = topicRepository.findSubcommentById(request.id())
                .orElseThrow(() -> new RuntimeException("Subcomment not found"));
        topicRepository.addSubcommentToComment(subcomment.update(request));
        return SubcommentResponse.of(subcomment);
    }
}
