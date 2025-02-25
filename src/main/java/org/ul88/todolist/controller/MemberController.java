package org.ul88.todolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ul88.todolist.dto.MemberDto;
import org.ul88.todolist.service.MemberService;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;

    @GetMapping("/auth/login")
    public void login(HttpServletRequest request, Model model){
        log.info("login 실행");

        Object errorMsg = request.getSession().getAttribute("errorMsg");

        if(errorMsg != null){
            model.addAttribute("errorMsg", errorMsg.toString());
            request.getSession().removeAttribute("errorMsg");
        }
    }

    @GetMapping("/auth/signup")
    public void signup(){
        log.info("signup");
    }

    @PostMapping("/auth/signup")
    public String signup(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String repassword){
        if(service.signUp(username, password, repassword)){
            return "redirect:/auth/login";
        }else{
            return "redirect:/auth/signup";
        }
    }

    @GetMapping({"/","/list"})
    public String list(Model model){
        log.info("list.....");

        List<MemberDto> memberList = service.searchAllUsers();

        model.addAttribute("list", memberList);
        return "todo/list";
    }

    @PostMapping("/auth/fail")
    @ResponseBody
    public String fail(HttpServletRequest request){
        return request.getAttribute("errorMsg").toString();
    }
}
