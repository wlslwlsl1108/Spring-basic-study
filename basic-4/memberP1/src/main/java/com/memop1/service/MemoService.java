package com.memop1.service;

import com.memop1.dto.MemoRequest;
import com.memop1.dto.MemoResponse;
import com.memop1.entity.Memo;
import com.memop1.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    // CRUD - "C (Create)"  => 생성
    @Transactional
    public MemoResponse save(MemoRequest memoRequest) {
        Memo savedMemo = memoRepository.save(new Memo(memoRequest.getContent()));

        return new MemoResponse(
                savedMemo.getId(),
                savedMemo.getContent()
        );
    }

    // CRUD - "R (Read)"  => 전체 조회
    @Transactional(readOnly = true)
    public List<MemoResponse> findMemos() {
        List<Memo> memos = memoRepository.findAll();
        List<MemoResponse> dtos = new ArrayList<>();

        for (Memo memo : memos) {
            MemoResponse memoResponse = new MemoResponse(
                    memo.getId(),
                    memo.getContent()
            );
            dtos.add(memoResponse);
        }
        return dtos;
    }

    // CRUD - "U (Update)"  => 수정
    @Transactional
    public MemoResponse update(Long memoId, MemoRequest memoRequest) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memoId가 없습니다.")
        );
        memo.updateContent(memoRequest.getContent());
        return new MemoResponse(
                memo.getId(),
                memo.getContent()
        );
    }

    // CRUD - "D (Delete)"  => 삭제
    @Transactional
    public void deleteMemo(Long memoId) {
        boolean b = memoRepository.existsById(memoId);
        if (!b) {
            throw new IllegalArgumentException(("해당하는 memoId가 없습니다."));
        }
        memoRepository.deleteById(memoId);
    }

    // CRUD - "R (Read)"  => 단건 조회
    @Transactional(readOnly = true)
    public MemoResponse findMemo(Long memoId) {

        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memoId가 없습니다.")
        );

        return new MemoResponse(memo.getId(), memo.getContent());
    }
}
