package org.ul88.todolist.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.ul88.todolist.entity.MemberEntity;
import org.ul88.todolist.repository.MemberRepository;
import org.ul88.todolist.dto.MemberUserDetails;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberEntity> res = memberRepository.findByName(username);

        if(res.isEmpty()){
            throw new UsernameNotFoundException(username);
        }

        MemberEntity member = res.get();

        return MemberUserDetails.builder()
                .username(member.getName())
                .password(member.getPassword())
                .role(member.getRole())
                .build();

    }
}
