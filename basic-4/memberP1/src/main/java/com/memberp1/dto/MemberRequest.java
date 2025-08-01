package com.memberp1.dto;

import lombok.Getter;

// id는 DB에서 만들어주니 삭제
// 생성자 x , final x  => 이유는 나중에..
@Getter
public class MemberRequest {

    private String name;
}
