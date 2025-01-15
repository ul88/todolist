package org.ul88.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ul88.todolist.dto.MemberDto;
import org.ul88.todolist.dto.TodoDto;
import org.ul88.todolist.entity.MemberEntity;
import org.ul88.todolist.entity.TodoEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberTodoService {
    private final MemberService memberService;
    private final TodoService todoService;

    public List<TodoDto> findByMember(String name){
        return todoService.findByMemberTodo(name);
    }

    public void todoChange(Long id){
        todoService.change(id);
    }

    public void todoDelete(Long id){
        todoService.delete(id);
    }

    public void todoCreate(String name, String content){
        MemberEntity member = memberService.searchMemberByName(name);
        if(member == null){
            return;
        }
        todoService.create(member, content);
    }

    public void todoUpdate(Long id, String content){
        todoService.update(id, content);
    }
}
