package org.ul88.todolist.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoDto {
    private Long id;
    private String content;
    private Boolean completed;
    private MemberDto member;
}
