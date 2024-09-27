package com.example.myconvention.topic.service;

import com.example.myconvention.global.dto.response.result.ListResult;
import com.example.myconvention.global.dto.response.result.SingleResult;
import com.example.myconvention.global.exception.CustomException;
import com.example.myconvention.global.exception.ErrorCode;
import com.example.myconvention.global.service.ResponseService;
import com.example.myconvention.topic.dto.request.TopicCreateRequest;
import com.example.myconvention.topic.dto.request.TopicUpdateRequest;
import com.example.myconvention.topic.dto.response.TopicResponse;
import com.example.myconvention.topic.entity.Topic;
import com.example.myconvention.topic.mapper.TopicMapper;
import com.example.myconvention.topic.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    public SingleResult<Long> save(TopicCreateRequest request) {
        Topic savedTopic = topicRepository.save(TopicMapper.from(request));
        return ResponseService.getSingleResult(savedTopic.getId());
    }

    public SingleResult<TopicResponse> findById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        TopicResponse topicResponse = TopicResponse.of(topic);
        return ResponseService.getSingleResult(topicResponse);
    }

    public ListResult<TopicResponse> findAll() {
        List<Topic> topics = topicRepository.findAll();
        List<TopicResponse> list = topics.stream().map(TopicResponse::of).toList();
        return ResponseService.getListResult(list);
    }

    public SingleResult<TopicResponse> update(TopicUpdateRequest request) {
        Topic topic = topicRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Topic not found"));
        topicRepository.save(topic.update(request));
        TopicResponse topicResponse = TopicResponse.of(topic);
        return ResponseService.getSingleResult(topicResponse);
    }

    public SingleResult<Long> remove(Long id) {
        Long deletedId = topicRepository.deleteById(id);
        return ResponseService.getSingleResult(deletedId);
    }
}
