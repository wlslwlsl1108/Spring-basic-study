package com.memop1.dto;

import lombok.Getter;

@Getter
public class MemoResponse {

    private final Long id;
    private final String title;
    private final String content;

    public MemoResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
