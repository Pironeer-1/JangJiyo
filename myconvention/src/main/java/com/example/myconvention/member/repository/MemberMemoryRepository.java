package com.example.myconvention.member.repository;

import com.example.myconvention.member.entity.Member;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberMemoryRepository implements MemberRepository {
    private final AtomicLong memberIdxGenerator = new AtomicLong(0);
    private final Map<Long, Member> memberMap = new HashMap<>();

    @Override
    public Member signup(Member member) {
        if (member.getMemberId() == null) {
            Long id = memberIdxGenerator.incrementAndGet();
            member.setId(id);
            memberMap.put(id, member);
            return member;
        } else {
            memberMap.replace(member.getId(), member);
            return member;
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        Assert.notNull(id, "Id must not be Null");
        return Optional.ofNullable(memberMap.get(id));
    }

    @Override
    public Optional<Member> findByMemberId(String memberId) {
        return memberMap.values().stream()
                .filter(data -> data.getMemberId().equals(memberId))
                .findAny();
    }

    @Override
    public Boolean existByMemberId(String memberId) {
        return memberMap.values().stream()
                .anyMatch(data -> data.getMemberId().equals(memberId));
    }
}
