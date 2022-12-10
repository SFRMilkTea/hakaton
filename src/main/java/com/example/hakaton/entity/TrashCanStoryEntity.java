package com.example.hakaton.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TrashCanStoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long trashCanId;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private String operation;
    @Column(nullable = false)
    private String userEmail;

    public TrashCanStoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrashCanId() {
        return trashCanId;
    }

    public void setTrashCanId(Long trashCanId) {
        this.trashCanId = trashCanId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
