package com.project.tvp.dao;


import com.project.tvp.entity.TakeNotesEntity;
import com.project.tvp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<TakeNotesEntity, Long> {
    List<TakeNotesEntity> findByUser(UserEntity user);
    List<TakeNotesEntity> findByTitleContaining(String title);
    List<TakeNotesEntity> findAllByOrderByUserDesc();
    List<TakeNotesEntity> findAllByOrderByUserAsc();
    List<TakeNotesEntity> findAllByOrderByDateCreateDesc();
    List<TakeNotesEntity> findAllByOrderByDateCreateAsc();

//    @Modifying
//    @Query("update TakeNotesEntity n set n.title = ?1, n.content = ?2 where n.id = ?3")
//    int setNoteById(String title, String content, Long id);
}
