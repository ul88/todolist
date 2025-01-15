package org.ul88.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.ul88.todolist.dto.TodoDto;
import org.ul88.todolist.entity.MemberEntity;
import org.ul88.todolist.entity.MemberRole;
import org.ul88.todolist.entity.TodoEntity;
import org.ul88.todolist.repository.MemberRepository;
import org.ul88.todolist.repository.TodoRepository;
import org.ul88.todolist.service.TodoService;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class EntityTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void saveTest(){
        memberRepository.save(MemberEntity.builder()
                        .name("id1")
                        .password(passwordEncoder.encode("123456"))
                        .role(MemberRole.UNKNOWN)
                .build());
    }

    @Test
    public void test(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            MemberEntity member = MemberEntity.builder()
                    .name("id"+i)
                    .password(passwordEncoder.encode("123456"))
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void insertTodo(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Optional<MemberEntity> res = memberRepository.findByName("id1");
            if(res.isPresent()){
                todoRepository.save(TodoEntity.builder()
                        .content("content"+i)
                        .completed(false)
                        .member(res.get())
                        .build());
            }
        });
    }

    @Test
    public void searchTest(){
        Optional<MemberEntity> member = memberRepository.findByName("id1");

        List<TodoEntity> todoEntityList = todoRepository.findByMember(member.get());

        for(TodoEntity todo : todoEntityList){
            System.out.println(todo.toString());
        }
    }

    @Test
    public void findAllTest(){
        List<TodoDto> todoList = todoService.findAll();
        for(TodoDto todo : todoList){
            System.out.println(todo);
        }
    }

    @Test
    public void findByTest(){
        List<TodoDto> todoList = todoService.findList("id1");
        for(TodoDto todo : todoList){
            System.out.println(todo);
        }
    }
}
