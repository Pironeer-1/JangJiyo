package com.example.myconvention.member.entity;

import lombok.Builder;
import lombok.Data;

@Data
public class Member {
    private Long id;
    private String memberId;
    private String password;
    private String name;

    @Builder
    public Member(Long id, String memberId, String password, String name) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
    }
}
