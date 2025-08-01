package com.memberp1.service;

import com.memberp1.dto.MemberRequest;
import com.memberp1.dto.MemberResponse;
import com.memberp1.entity.Member;
import com.memberp1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor //lombok 기능 -> 서비스랑 항상 같이 사용하기!
/* public MemberService(MemberRepository memberRepository)
       this.memberRepository = memberRepository;
   @RequiredArgsConstructor 써주고 위 생성자 정의 써주면 에러 발생 => "생성자 중복"
   생성자 정의 갯수가 많아져도 @RequiredArgsConstructor 써주면 편의성 증가! */
public class MemberService {

    private final MemberRepository memberRepository;

    // CRUD - "C (Create)"  => 생성(멤버 저장 기능)
    @Transactional
    public MemberResponse save(MemberRequest memberRequest) {
        Member savedMember = memberRepository.save(new Member(memberRequest.getName()));
        /* Member member = new Member(memberRequest.getName());
           Member savedMember = memberRepository.save(memter);  */

        return new MemberResponse(
                savedMember.getId(),
                savedMember.getName()
        );
    }

    // CRUD - "R (Read)"  => 전체 조회
    @Transactional(readOnly = true)
    public List<MemberResponse> findMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> dtos = new ArrayList<>();

        for (Member member : members) {
            MemberResponse memberResponse = new MemberResponse(
                    member.getId(),
                    member.getName()
            );
            dtos.add(memberResponse);
        }
        return dtos;
    }

    // CRUD - "U (Update)"  => 수정
    @Transactional
    public MemberResponse update(Long memberId, MemberRequest memberRequest) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memberID가 없습니다.")
        );
        member.updateName(memberRequest.getName());
        return new MemberResponse(
                member.getId(),
                member.getName()
        );
    }

    // CRUD - "D (Delete)"  => 삭제
    @Transactional
    public void deleteMember(Long memberId) {
        boolean b = memberRepository.existsById(memberId);
        if (!b) {
            throw new IllegalArgumentException("해당하는 memberID가 없습니다.");
        }
        memberRepository.deleteById(memberId);
    }

    // CRUD - "R (Read)"  => 단건 조회
    @Transactional(readOnly = true)
    public MemberResponse findMember(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 memberID가 없습니다.")
        );

        return new MemberResponse(member.getId(), member.getName());
    }
}






