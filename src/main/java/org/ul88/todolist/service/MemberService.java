package org.ul88.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.ul88.todolist.dto.MemberDto;
import org.ul88.todolist.entity.MemberEntity;
import org.ul88.todolist.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public boolean signUp(String name, String password, String repassword){
        if(repository.findByName(name).isPresent() && password.equals(repassword)){
            return false;
        }

        repository.save(MemberEntity.builder()
                        .name(name)
                        .password(passwordEncoder.encode(password))
                .build());

        return true;
    }

    public List<MemberDto> searchAllUsers(){
        List<MemberEntity> memberEntity = repository.findAll();

        return memberEntity.stream().map(m-> new MemberDto(
                m.getName(),
                m.getRole()
        )).collect(Collectors.toList());
    }

    public MemberEntity searchMemberByName(String name){
        Optional<MemberEntity> memberEntity = repository.findByName(name);
        if(memberEntity.isPresent()){
            return memberEntity.get();
        }else{
            return null;
        }
    }
}
