package com.example.club_member_project.club.model;

import com.example.club_member_project.member.db.MemberEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ClubDto {

    private Long id;

    private String name;

    private String description;

    // private List<MemberEntity> members = List.of();
}
