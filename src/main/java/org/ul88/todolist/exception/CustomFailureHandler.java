package org.ul88.todolist.exception;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import java.io.IOException;

@Log4j2
public class CustomFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg;

        if(exception instanceof BadCredentialsException){
            errorMsg = "Bad credentials";
        }
        else if(exception instanceof UsernameNotFoundException){
            errorMsg = "User not found";
        }
        else if(exception instanceof SessionAuthenticationException){
            errorMsg = "Authentication failure";
        }else{
            errorMsg = "ERROR";
        }

        log.info("exception: " + errorMsg);
        request.getSession().setAttribute("errorMsg", errorMsg);
        response.sendRedirect("/auth/login?error");
    }
}
