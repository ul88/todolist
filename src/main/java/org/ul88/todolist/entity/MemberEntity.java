package org.ul88.todolist.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberEntity {
    @Id
    @Column(length = 128)
    private String name;

    @Column(length = 128, nullable = false)
    private String password;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private MemberRole role = MemberRole.USER;
}
