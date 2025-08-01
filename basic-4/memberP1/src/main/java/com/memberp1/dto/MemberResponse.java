package com.memberp1.dto;

import lombok.Getter;

@Getter// 꼭!! 같이 붙여주기. -> 안붙이면 오류발생 (실행x)
public class MemberResponse {

    // final 붙이는 이유? -> MemberResponse는 한번 만들고나면 변경할 일 없기 때문
    private final Long id;
    private final String name;

    public MemberResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
