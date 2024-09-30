package com.example.myconvention.member.service;

import com.example.myconvention.global.dto.response.JwtTokenSet;
import com.example.myconvention.global.dto.response.result.SingleResult;
import com.example.myconvention.global.exception.CustomException;
import com.example.myconvention.global.exception.ErrorCode;
import com.example.myconvention.global.service.AuthService;
import com.example.myconvention.global.service.ResponseService;
import com.example.myconvention.member.dto.request.MemberCreateRequest;
import com.example.myconvention.member.dto.request.MemberLoginRequest;
import com.example.myconvention.member.entity.Member;
import com.example.myconvention.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthService authService;

    public SingleResult<JwtTokenSet> signup(MemberCreateRequest request) {
        // memberId 중복 체크
        if (memberRepository.existByMemberId(request.memberId())) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
        }

        Member newMember = Member.builder()
                .memberId(request.memberId())
                .password(request.password())
                .name(request.name())
                .build();
        newMember = memberRepository.signup(newMember);

        JwtTokenSet jwtTokenSet = authService.generateToken(newMember.getId());

        return ResponseService.getSingleResult(jwtTokenSet);
    }

    public SingleResult<JwtTokenSet> login(MemberLoginRequest request) {
        Member member = memberRepository.findByMemberId(request.memberId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXIST));

        // password 검증
        if (!member.getPassword().equals(request.password())) {
            throw new CustomException(ErrorCode.USER_WRONG_PASSWORD);
        }

        JwtTokenSet jwtTokenSet = authService.generateToken(member.getId());

        return ResponseService.getSingleResult(jwtTokenSet);
    }
}
