package com.example.FinanceApp.dto;

import com.example.FinanceApp.prototype.Prototype;

public class UserDTO implements Prototype {
    private Long id;
    private String name;
    private String email;

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    //Tydzień 1, Wzorzec Prototype 1, klonowanie usera na podstawie UserDTO
    @Override
    public Prototype clone() {
        try {
            return (UserDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning failed", e);
        }
    }
    //Koniec, Tydzień 1, Wzorzec Prototype 1


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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
}
