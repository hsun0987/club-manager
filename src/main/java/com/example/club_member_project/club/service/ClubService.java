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

    public ClubEntity create(
            ClubRequest clubRequest
    ){
        var entity = ClubEntity.builder()
                .name(clubRequest.getName())
                .description(clubRequest.getDescription())
                .build();

        return clubRepository.save(entity);
    }

    public List<ClubEntity> getAllClubs() {
        return clubRepository.findAll();
    }

    public void update(Long id, ClubEntity updateClub) {
        ClubEntity existing = clubRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Club not found"));

        existing.setName(updateClub.getName());
        existing.setDescription(updateClub.getDescription());

        clubRepository.save(existing);
    }

    public void delete(Long id) {
        ClubEntity club = clubRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Club not found"));

        clubRepository.delete(club);
    }
}
