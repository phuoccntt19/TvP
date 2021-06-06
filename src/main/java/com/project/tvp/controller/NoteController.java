package com.project.tvp.controller;

import com.project.tvp.dao.NoteRepository;
import com.project.tvp.dao.UserRepository;
import com.project.tvp.entity.TakeNotesEntity;
import com.project.tvp.service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteServiceImpl noteServiceImpl;

    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    public String postNote(TakeNotesEntity takeNotesEntity, Principal principal) {
        noteServiceImpl.postNote(takeNotesEntity, userRepository.findByUsername(principal.getName()));
        return "redirect:/user";
    }

    @GetMapping("")
    public String getNotes(Model model) {
        model.addAttribute("listNote", noteRepository.findAll());
        return "note_page";
    }

    @GetMapping("/find")
    public String findNotes(Model model, @RequestParam(name = "title") String title) {
        model.addAttribute("listNote", noteRepository.findByTitleContaining(title));
        return "note_page";
    }

    @GetMapping("/sort")
    public String sortNotes(Model model, @RequestParam(name = "value") String sort) {
        if(sort.equals("dateCreated")) {
            model.addAttribute("listNote", noteRepository.findAllByOrderByDateCreateAsc());
        } else {
            model.addAttribute("listNote", noteRepository.findAllByOrderByDateCreateDesc());
        }
        return "note_page";
    }

    @GetMapping("/filter")
    public String filterNotes(Model model, @RequestParam(name = "username") String username) {
        model.addAttribute("listNote", noteRepository.findByUser(userRepository.findByUsername(username)));
        return "note_page";
    }

    @GetMapping("/delete")
    public String findNotes(Model model, @RequestParam Long id) {
        noteRepository.delete(noteRepository.findById(id).get());
        return "redirect:/user";
    }

    @PostMapping("/repair")
    public String repairMote(TakeNotesEntity note) {
        noteServiceImpl.updateNote(note);
        return "redirect:/user";
    }
}
