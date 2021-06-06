package com.project.tvp.service;

import com.project.tvp.entity.TakeNotesEntity;
import com.project.tvp.entity.UserEntity;

public interface NoteService {
    TakeNotesEntity postNote(TakeNotesEntity takeNotesEntity, UserEntity user);
    TakeNotesEntity updateNote(TakeNotesEntity note);
}
