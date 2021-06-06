package com.project.tvp.service;

import com.project.tvp.dao.NoteRepository;
import com.project.tvp.entity.TakeNotesEntity;
import com.project.tvp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Override
    public TakeNotesEntity postNote(TakeNotesEntity takeNotesEntity, UserEntity user) {
        takeNotesEntity.setDateCreate(LocalDateTime.now());
        takeNotesEntity.setUser(user);
        noteRepository.save(takeNotesEntity);
        return takeNotesEntity;
    }

    @Override
    public TakeNotesEntity updateNote(TakeNotesEntity newNote) {
        TakeNotesEntity oldNote = noteRepository.findById(newNote.getId()).get();
        newNote.setUser(oldNote.getUser());
        newNote.setDateCreate(oldNote.getDateCreate());
        noteRepository.save(newNote);
        return null;
    }
}
