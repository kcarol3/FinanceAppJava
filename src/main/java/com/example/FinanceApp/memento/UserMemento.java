package com.example.FinanceApp.memento;

import com.example.FinanceApp.entity.base.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

//Tydzien 4, Wzorzec Memento 2, tworzenie kopii usera
@Entity
public class UserMemento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    private User user;

    public UserMemento(String name, String email, User user) {
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
        this.user = user;
    }

    public UserMemento() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public User getUser() { return user; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setUser(User user) { this.user = user; }
}
//Koniec, Tydzien 4, Wzorzec Memento 2
