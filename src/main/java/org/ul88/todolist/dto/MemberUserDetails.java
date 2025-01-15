package org.ul88.todolist.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.ul88.todolist.entity.MemberRole;

import java.util.ArrayList;
import java.util.Collection;

@Builder
@EqualsAndHashCode(of = "username")
public class MemberUserDetails implements UserDetails {

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("ROLE_" + role));
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
