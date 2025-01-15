package org.ul88.todolist.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth)->{
            auth.requestMatchers("/todo/**").authenticated();
            auth.anyRequest().permitAll();
        });

        http.sessionManagement(session->session
                .maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/login")
        );
        http.csrf(csrf->csrf.disable());
        http.formLogin(form->form
                .loginPage("/auth/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll()
        );
        http.logout(form->form
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/")
        );
        return http.build();
    }
}
