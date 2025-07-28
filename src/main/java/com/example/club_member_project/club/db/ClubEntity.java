package com.example.club_member_project.club.db;

import com.example.club_member_project.member.db.MemberEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "club")
public class ClubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    // 1:N (board : post)
    @OneToMany(
            mappedBy = "club"
    )
    @Builder.Default
    @ToString.Exclude
    @JsonIgnore
    private List<MemberEntity> members = List.of();

}
