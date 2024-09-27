package com.example.myconvention.member.controller;

import com.example.myconvention.global.dto.response.JwtTokenSet;
import com.example.myconvention.global.dto.response.SuccessResponse;
import com.example.myconvention.global.dto.response.result.SingleResult;
import com.example.myconvention.member.dto.request.MemberCreateRequest;
import com.example.myconvention.member.dto.request.MemberLoginRequest;
import com.example.myconvention.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "회원(Member)")
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @Operation(summary = "회원가입")
    public SuccessResponse<SingleResult<JwtTokenSet>> signup(@Valid @RequestBody MemberCreateRequest request) {
        SingleResult<JwtTokenSet> result = memberService.signup(request);
        return SuccessResponse.ok(result);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public SuccessResponse<SingleResult<JwtTokenSet>> login(@Valid @RequestBody MemberLoginRequest request) {
        SingleResult<JwtTokenSet> result = memberService.login(request);
        return SuccessResponse.ok(result);
    }
}
