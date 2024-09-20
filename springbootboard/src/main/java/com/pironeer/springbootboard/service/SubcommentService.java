package com.pironeer.springbootboard.service;

import com.pironeer.springbootboard.dto.request.SubcommentCreateRequest;
import com.pironeer.springbootboard.dto.request.SubcommentUpdateRequest;
import com.pironeer.springbootboard.dto.response.SubcommentResponse;
import com.pironeer.springbootboard.repository.SubcommentRepository;
import com.pironeer.springbootboard.repository.domain.Subcomment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcommentService {
    private final SubcommentRepository subcommentRepository;

    public void addSubcommentToComment(SubcommentCreateRequest request) {
        Subcomment subcomment = Subcomment.builder()
                .commentId(request.commentId())
                .content(request.content())
                .build();
        subcommentRepository.addSubcommentToComment(subcomment);
    }

    public List<SubcommentResponse> readAllSubcomments(Long commentId) {
        List<Subcomment> subcomments = subcommentRepository.readAllSubcomments(commentId);
        return subcomments.stream().map(SubcommentResponse::of).toList();
    }

    public SubcommentResponse findSubcommentById(Long id) {
        Subcomment subcomment = subcommentRepository.findSubcommentById(id)
                .orElseThrow(() -> new RuntimeException("Subcomment not found"));
        return SubcommentResponse.of(subcomment);
    }

    public SubcommentResponse updateSubcomment(SubcommentUpdateRequest request) {
        Subcomment subcomment = subcommentRepository.findSubcommentById(request.id())
                .orElseThrow(() -> new RuntimeException("Subcomment not found"));
        subcommentRepository.addSubcommentToComment(subcomment.update(request));
        return SubcommentResponse.of(subcomment);
    }

    public void deleteSubcomment(Long id) {
        subcommentRepository.deleteSubcomment(id);
    }
}
