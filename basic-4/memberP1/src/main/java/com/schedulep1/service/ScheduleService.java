package com.schedulep1.service;

import com.schedulep1.dto.ScheduleRequest;
import com.schedulep1.dto.ScheduleResponse;
import com.schedulep1.dto.ScheduleUpRequest;
import com.schedulep1.dto.ScheduleUpResponse;
import com.schedulep1.entity.Schedule;
import com.schedulep1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // CRUD - "C (Create)"  => 생성
    @Transactional
    public ScheduleResponse save(ScheduleRequest scheduleRequest) {
        Schedule savedSchedule = scheduleRepository.save(
                new Schedule(scheduleRequest.getTitle(),
                             scheduleRequest.getContent(),
                             scheduleRequest.getName(),
                             scheduleRequest.getPassword()));

        return new ScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getName(),
                savedSchedule.getPassword()
        );
    }

    // CRUD - "R (Read)"  => 전체 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponse> findSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            ScheduleResponse scheduleResponse = new ScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getPassword()
            );
            dtos.add(scheduleResponse);
        }
        return dtos;
    }

    // CRUD - "U (Update)"  => 수정
    @Transactional
    public ScheduleUpResponse update(Long scheduleId, ScheduleUpRequest scheduleUpRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 scheduleId가 없습니다.")
        );
        schedule.updateContent(
                scheduleUpRequest.getTitle(),
                scheduleUpRequest.getName()
        );
        return new ScheduleUpResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getName()
        );
    }

    // CRUD - "D (Delete)"  => 삭제
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        boolean b = scheduleRepository.existsById(scheduleId);
        if (!b) {
            throw new IllegalArgumentException("해당하는 scheduleId가 없습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }

    // CRUD - "R (Read)"  => 단건 조회
    @Transactional(readOnly = true)
    public ScheduleResponse findSchedule(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 scheduleId가 없습니다.")
        );

        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getPassword()
        );
    }
}
