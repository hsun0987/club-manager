package com.example.club_member_project.member.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 동아리별 회원 목록
    List<MemberEntity> findByClubId(Long clubId);

    // 동아리별 회원 수
    long countByClubId(Long clubId);
}
