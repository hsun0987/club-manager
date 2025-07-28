package com.example.club_member_project.member.model;

import com.example.club_member_project.club.db.ClubEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberDto {

    private Long id;

    private String name;

    private String email;

    private Long clubId;

    private String clubName;
}
