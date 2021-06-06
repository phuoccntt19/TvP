package com.project.tvp.controller;

import com.project.tvp.dao.UserRepository;
import com.project.tvp.entity.UserEntity;
import com.project.tvp.entity.UserInfoEntity;
import com.project.tvp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String welcomePage(Model model, Principal principal) {
        model.addAttribute("message", "Hello!");
        if(principal != null) {
            model.addAttribute("idUser", userRepository.findByUsername(principal.getName()).getId());
        }
        return "welcome_page";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "register_page";
    }

    @PostMapping("/register")
    public String processRegister(UserEntity user, UserInfoEntity userInfo, Model model) {
        if (userServiceImpl.register(user, userInfo)) {
            model.addAttribute("message", "Successful registration");
            return "200_page";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("message", "Registration failed!!!");
            return "register_page";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }

    @GetMapping("/403")
    public String page403(Model model) {
        model.addAttribute("message", "Unauthorized");
        return "403_page";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<UserEntity> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin_page";
    }
}
