package com.example.ss9.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Boolean active;
    private String avatar;

    public User(Integer id, String username, String password, Boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.avatar = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSzkzzbCAzkVsfPP_jfIGUBMR5K64qW6FiK4-03YBv2ou2Spx7NC8p12MpRjAjDLBKjt4_D626ve7zwQWllqi1YB1jj-kZiPOdoe7jcQ&s=10";
    }
}
