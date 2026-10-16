package com.example.ss9.services;

import com.example.ss9.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> userList = new ArrayList<>();

    public UserService() {
        User u1 = new User(
                1,
                "admin",
                "123123",
                true,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSzkzzbCAzkVsfPP_jfIGUBMR5K64qW6FiK4-03YBv2ou2Spx7NC8p12MpRjAjDLBKjt4_D626ve7zwQWllqi1YB1jj-kZiPOdoe7jcQ&s=10"
        );
        User u2 = new User(
                2,
                "admin2",
                "123123",
                true,
                "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&w=150&q=80"
        );

        userList.add(u1);
        userList.add(u2);
    }

    public List<User> getUserList() {
        return this.userList;
    }

    public User findById(Integer id) {
        if (id == null) return null;
        return userList.stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteUser(Integer id) {
        if (id == null) return false;
        return userList.removeIf(user -> id.equals(user.getId()));
    }

    public boolean updateUser(User updatedUser) {
        if (updatedUser == null || updatedUser.getId() == null) return false;
        for (int i = 0; i < userList.size(); i++) {
            if (updatedUser.getId().equals(userList.get(i).getId())) {
                User existing = userList.get(i);
                existing.setUsername(updatedUser.getUsername());
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
                    existing.setPassword(updatedUser.getPassword());
                }
                existing.setActive(updatedUser.getActive() != null ? updatedUser.getActive() : false);
                if (updatedUser.getAvatar() != null && !updatedUser.getAvatar().isBlank()) {
                    existing.setAvatar(updatedUser.getAvatar());
                }
                return true;
            }
        }
        return false;
    }
}
