package com.project.tvp.entity;

import com.project.tvp.dao.NoteRepository;
import com.project.tvp.service.NoteServiceImpl;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "take_notes")
public class TakeNotesEntity {
    @Id
    @GeneratedValue
    @Column(name = "take_notes_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 9999)
    private String title;

    @Column(name = "content", nullable = false, length = 999999)
    private String content;

    @Column(name = "date_create", nullable = false)
    private LocalDateTime dateCreate;

    //n:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDateFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateCreate.format(formatter);
    }

    @Override
    public String toString() {
        return "TakeNotesEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateCreate=" + dateCreate +
                ", user=" + user +
                '}';
    }
}
