package com.example.club_member_project.member.service;

import com.example.club_member_project.club.db.ClubEntity;
import com.example.club_member_project.club.db.ClubRepository;
import com.example.club_member_project.member.db.MemberEntity;
import com.example.club_member_project.member.db.MemberRepository;
import com.example.club_member_project.member.model.MemberDto;
import com.example.club_member_project.member.model.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final ClubRepository clubRepository;

    // 회원 생성
    public MemberDto create(Long clubId, MemberRequest req) {
        ClubEntity club = clubRepository.findById(clubId)
                .orElseThrow(() -> new NoSuchElementException("Club not found"));

        MemberEntity saved = memberRepository.save(
                MemberEntity.builder()
                        .name(req.getName())
                        .email(req.getEmail())
                        .club(club)
                        .build()
        );

        return toDto(saved);
    }

    // 회원 정보 수정
    public MemberDto update(Long id, MemberRequest req) {
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Member not found"));

        member.setName(req.getName());
        member.setEmail(req.getEmail());

        return toDto(memberRepository.save(member));
    }

    // 회원 조회
    // 전체 목록 조회
    public List<MemberDto> getAllByClub(Long clubId) {
        return memberRepository.findByClubId(clubId).stream()
                .map(this::toDto)
                .toList();
    }

    // 회원 1명 조회
    public MemberDto getOneMember(Long id) {
        return memberRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new NoSuchElementException("Member not found"));
    }

    // 회원 삭제
    public void delete(Long id) {
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Member not found"));

        memberRepository.delete(member);
    }

    // entity -> dto
    private MemberDto toDto(MemberEntity e) {
        return MemberDto.builder()
                .id(e.getId())
                .name(e.getName())
                .email(e.getEmail())
                .clubId(e.getClub().getId())
                .clubName(e.getClub().getName())
                .build();
    }
}
