package com.schedulep1.dto;

import lombok.Getter;

@Getter
public class ScheduleUpResponse {

    private final Long id;
    private final String title;
    private final String name;

    public ScheduleUpResponse(Long id, String title, String name) {
        this.id = id;
        this.title = title;
        this.name = name;
    }
}

