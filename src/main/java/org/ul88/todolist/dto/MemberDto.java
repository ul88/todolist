package org.ul88.todolist.dto;

import jakarta.persistence.*;
import lombok.*;
import org.ul88.todolist.entity.MemberEntity;
import org.ul88.todolist.entity.MemberRole;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
    private String name;
    private MemberRole role;
}
