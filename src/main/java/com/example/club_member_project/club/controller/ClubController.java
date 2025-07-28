package com.example.club_member_project.club.controller;

import com.example.club_member_project.club.db.ClubEntity;
import com.example.club_member_project.club.model.ClubRequest;
import com.example.club_member_project.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/club")
public class ClubController {

    private final ClubService clubService;

    // 동아리 생성
    @PostMapping("")
    public ClubEntity createClub(
            @RequestBody ClubRequest clubRequest
    ){
        return clubService.create(clubRequest);
    }


    // 동아리 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClub(
            @PathVariable Long id,
            @RequestBody ClubEntity updateClub
    ){
        try {
            clubService.update(id, updateClub);
            return ResponseEntity.ok("수정 완료");
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 동아리가 없습니다.");
        }
    }


    // 동아리 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClub(
        @PathVariable Long id
    ){
        try {
            clubService.delete(id);
            return ResponseEntity.ok("삭제 완료");
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 동아리가 없습니다.");
        }
    }

    // 동아리 목록 조회
    @GetMapping("")
    public List<ClubEntity> getAllClubs(){
        return clubService.getAllClubs();
    }
}
