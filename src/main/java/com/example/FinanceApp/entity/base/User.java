package com.example.FinanceApp.entity.base;

import com.example.FinanceApp.entity.Alert;
import com.example.FinanceApp.memento.AccountMemento;
import com.example.FinanceApp.memento.UserMemento;
import com.example.FinanceApp.state.UserStateType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserMemento> mementos;

    @Enumerated(EnumType.STRING)
    private UserStateType state;

    public User() {}

    public User(String name, String email, UserStateType state) {
        this.name = name;
        this.email = email;
        this.state = UserStateType.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() { return accounts; }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<UserMemento> getMementos() { return mementos;}

    public void addMementos(UserMemento memento) { mementos.add(memento); }
    public void restoreFromMemento(UserMemento memento) { this.name = memento.getName(); this.email = memento.getEmail(); }

    public UserStateType getState() { return state; }

    public void setState(UserStateType state) { this.state = state;}

    //Tydzień 1, Wzorzec Builder 1, tworzenie użytkowników
    private User(UserBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.state = builder.state;
    }

    public static class UserBuilder {
        private String name;
        private String email;
        private UserStateType state;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setState(UserStateType state) {
            this.state = state;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }
    //Koniec, Tydzień 1, Wzorzec Builder 1
}