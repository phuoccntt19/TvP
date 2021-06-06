package com.project.tvp.controller;

import com.project.tvp.dao.NoteRepository;
import com.project.tvp.dao.UserInfoRepository;
import com.project.tvp.dao.UserRepository;
import com.project.tvp.entity.TakeNotesEntity;
import com.project.tvp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    NoteRepository noteRepository;

    @GetMapping("")
    public String userPage(Model model, Principal principal) {
        model.addAttribute("note", new TakeNotesEntity());
        model.addAttribute("listNote", noteRepository.findByUser(userRepository.findByUsername(principal.getName())));
        return "user_page";
    }

    @GetMapping("/info/{id}")
    public String userInfoPage(Model model, @PathVariable Long id) {
        model.addAttribute("user", userRepository.findById(id).get());
        model.addAttribute("userInfo", userInfoRepository.findByUserEntity(userRepository.findById(id).get()));
        return "user_info_page";
    }

    @GetMapping("/my_info")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String myUserInfoPage(Model model, Principal principal) {
        if(principal == null) {
            return "redirect:/login";
        }
        UserEntity user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userInfo", userInfoRepository.findByUserEntity(user));
        return "user_info_page";
    }
}
