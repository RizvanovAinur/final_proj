package com.example.final_proj.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message="Логин не может быть пустым")
    @Size(min=5, max=100, message="Длина логина должна быть длиной 5-100 символов")
    @Column(name="login")
    private String login;
    @NotEmpty(message="Пароль не может быть пустым")
    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}