package com.example.myconvention.member.repository;

import com.example.myconvention.member.entity.Member;

import java.util.Optional;

public interface MemberRepository {
    Member signup(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByMemberId(String memberId);

    Boolean existByMemberId(String memberId);
}
