package com.example.club_member_project.member.controller;

import com.example.club_member_project.member.model.MemberDto;
import com.example.club_member_project.member.model.MemberRequest;
import com.example.club_member_project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    // 회원 등록
    @PostMapping("/clubs/{clubId}")
    public ResponseEntity<MemberDto> createMember(
            @PathVariable Long clubId,
            @RequestBody MemberRequest request
    ){
        return ResponseEntity.ok(memberService.create(clubId, request));
    }

    // 회원 정보 수정
    @PutMapping("/{id}")
    public MemberDto updateMember(
            @PathVariable Long id,
            @RequestBody MemberRequest request
    ){
        return memberService.update(id, request);
    }

    // 회원 전체 목록 조회
    @GetMapping("/clubs/{clubId}")
    public List<MemberDto> listByClub(
            @PathVariable Long clubId
    ){
        return memberService.getAllByClub(clubId);
    }

    // 회원 정보 조회
    @GetMapping("/{id}")
    public MemberDto oneByMember (
            @PathVariable Long id
    ){
        return memberService.getOneMember(id);
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(
            @PathVariable Long id
    ){
        memberService.delete(id);
        return ResponseEntity.ok().build();
    }

}
