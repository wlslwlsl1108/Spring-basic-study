package com.memop1.controller;

import com.memop1.dto.MemoRequest;
import com.memop1.dto.MemoResponse;
import com.memop1.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    // CRUD - "C (Create)"  => 생성
    @PostMapping("/memos")
    public MemoResponse createMemo(
            @RequestBody MemoRequest memoRequest
    ){
        return memoService.save(memoRequest);
    }

    // CRUD - "R (Read)"  => 전체 조회
    @GetMapping("/memos")
    public List<MemoResponse> getMemos() {

        return memoService.findMemos();
    }

    // CRUD - "R (Read)"  => 단건 조회
    @GetMapping("/memos/{memoId}")
    public MemoResponse getMemo(
            @PathVariable Long memoId
    ){
        return memoService.findMemo(memoId);
    }

    // CRUD - "U (Update)"  => 수정
    @PutMapping("/memos/{memoId}")
    public MemoResponse updateMemo(
            @PathVariable Long memoId,
            @RequestBody MemoRequest memoRequest
    ){
        return memoService.update(memoId, memoRequest);
    }

    // CRUD - "D (Delete)"  => 삭제
    @DeleteMapping("/memos/{memoId}")
    public void deleteMemo(
            @PathVariable Long memoId
    ){
        memoService.deleteMemo(memoId);
    }
}
