package org.ul88.todolist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.ul88.todolist.dto.TodoDto;
import org.ul88.todolist.service.MemberTodoService;
import org.ul88.todolist.service.TodoService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
@Log4j2
public class TodoController {
    private final MemberTodoService service;

    @GetMapping("/content")
    public String content(@RequestParam String name, Model model){
        List<TodoDto> todoList = service.findByMember(name);
        model.addAttribute("name", name);
        model.addAttribute("todoList", todoList);
        return "/todo/content";
    }

    @PostMapping("/change")
    public String change(@RequestParam Long id,
                         @RequestParam String name,
                         RedirectAttributes redirectAttributes){
        log.info("change: " + id+" "+name);
        service.todoChange(id);
        redirectAttributes.addAttribute("name", name);
        return "redirect:/todo/content";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id,
                         @RequestParam String name,
                         RedirectAttributes redirectAttributes){
        log.info("delete: "+id+" "+name);
        service.todoDelete(id);
        redirectAttributes.addAttribute("name", name);
        return "redirect:/todo/content";
    }

    @PostMapping("/create")
    public String create(@RequestParam String name,
                         @RequestParam String content,
                         RedirectAttributes redirectAttributes){
        log.info("create");
        service.todoCreate(name, content);
        redirectAttributes.addAttribute("name", name);
        return "redirect:/todo/content";
    }

    @GetMapping("/update")
    public String updatePage(@RequestParam String name,
                             @RequestParam Long id,
                             @RequestParam String content,
                             Model model,
                             RedirectAttributes redirectAttributes){
        if(SecurityContextHolder.getContext().getAuthentication().getName().equals(name)){
            log.info("updatePage");
            model.addAttribute("name", name);
            model.addAttribute("id", id);
            model.addAttribute("content", content);
            return "/todo/update";
        }else{
            log.info("updatePage fail");
            redirectAttributes.addAttribute("name", name);
            return "redirect:/todo/content";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam String name,
                         @RequestParam Long id,
                         @RequestParam String content,
                         RedirectAttributes redirectAttributes){
        log.info("update");
        service.todoUpdate(id, content);
        redirectAttributes.addAttribute("name", name);
        return "redirect:/todo/content";
    }
}
