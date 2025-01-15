package org.ul88.todolist.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ul88.todolist.entity.MemberEntity;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    Optional<MemberEntity> findByName(String name);
}
