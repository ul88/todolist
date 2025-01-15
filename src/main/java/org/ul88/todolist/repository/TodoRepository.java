package org.ul88.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ul88.todolist.entity.MemberEntity;
import org.ul88.todolist.entity.TodoEntity;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findByMember(MemberEntity member);

    List<TodoEntity> findByMember_Name(String name);
}
