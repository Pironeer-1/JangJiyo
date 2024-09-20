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
}
