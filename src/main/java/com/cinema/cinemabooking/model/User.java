package com.cinema.cinemabooking.model;

import com.cinema.cinemabooking.model.enums.UserRole;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Пользователь
 */
@Entity
public class User {

    /**
     * Идентификатор пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Адрес электронной почты
     */
    private String email;

    /**
     * Пароль
     */
    private String password;

    /**
     * Роль пользователя
     */
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String name;

    public User(String email, String name, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}