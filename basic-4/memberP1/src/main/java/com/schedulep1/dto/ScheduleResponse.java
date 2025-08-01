package com.schedulep1.dto;

import lombok.Getter;

@Getter
public class ScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final String password;

    public ScheduleResponse(Long id, String title, String content, String name, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }
}

