package org.ul88.todolist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.ul88.todolist.dto.MemberDto;
import org.ul88.todolist.dto.TodoDto;
import org.ul88.todolist.entity.MemberEntity;
import org.ul88.todolist.entity.TodoEntity;
import org.ul88.todolist.repository.MemberRepository;
import org.ul88.todolist.repository.TodoRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoService {
    private final TodoRepository repository;

    public List<TodoDto> findByMemberTodo(String name){
        List<TodoEntity> todo = repository.findByMember_Name(name);

        return todo.stream().map(m->new TodoDto(
                m.getId(),
                m.getContent(),
                m.getCompleted(),
                new MemberDto(m.getMember().getName(),m.getMember().getRole())
        )).collect(Collectors.toList());
    }

    public void change(Long id){
        Optional<TodoEntity> entity = repository.findById(id);
        if(entity.isPresent()){
            repository.save(TodoEntity.builder()
                            .id(entity.get().getId())
                            .content(entity.get().getContent())
                            .completed(!entity.get().getCompleted())
                            .member(entity.get().getMember())
                    .build());
        }
    }

    public void delete(Long id){
        Optional<TodoEntity> entity = repository.findById(id);
        if(entity.isPresent()){
            repository.delete(entity.get());
        }
    }

    public void create(MemberEntity member, String content){
        repository.save(TodoEntity.builder()
                        .member(member)
                        .content(content)
                        .completed(false)
                .build());
    }

    public void update(Long id, String content ){
        Optional<TodoEntity> entity = repository.findById(id);
        if(entity.isPresent()){
            repository.save(TodoEntity.builder()
                            .id(entity.get().getId())
                            .content(content)
                            .completed(entity.get().getCompleted())
                            .member(entity.get().getMember())
                    .build());
        }
    }
}
