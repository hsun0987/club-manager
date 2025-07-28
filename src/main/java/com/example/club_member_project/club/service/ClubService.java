package com.example.club_member_project.club.service;

import com.example.club_member_project.club.db.ClubEntity;
import com.example.club_member_project.club.db.ClubRepository;
import com.example.club_member_project.club.model.ClubDto;
import com.example.club_member_project.club.model.ClubRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    // 동아리 생성
    public ClubEntity create(
            ClubRequest clubRequest
    ){
        var entity = ClubEntity.builder()
                .name(clubRequest.getName())
                .description(clubRequest.getDescription())
                .build();

        return clubRepository.save(entity);
    }

    // 동아리 전체 조회
    public List<ClubDto> getAllClubs() {
        List<ClubEntity> clubs = clubRepository.findAll();

        return clubs.stream().map(club -> {
            return ClubDto.builder()
                    .id(club.getId())
                    .name(club.getName())
                    .description(club.getDescription())
                    .members(club.getMembers()) // 회원 목록도 같이 조회
                    .build();
        }).toList();

    }

    // 동아리 수정
    public void update(Long id, ClubEntity updateClub) {
        ClubEntity existing = clubRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Club not found"));

        existing.setName(updateClub.getName());
        existing.setDescription(updateClub.getDescription());

        clubRepository.save(existing);
    }

    // 동아리 삭제
    public void delete(Long id) {
        ClubEntity club = clubRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Club not found"));

        clubRepository.delete(club);
    }
}
