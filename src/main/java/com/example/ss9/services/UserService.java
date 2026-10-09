package com.example.ss9.services;

import com.example.ss9.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> userList = new ArrayList<>();

    public UserService() {
        User u1 = new User(1, "admin", "123123", true);
        User u2 = new User(1, "admin2", "123123", true);

        userList.add(u1);
        userList.add(u2);
    }


    public List<User> getUserList() {
        return this.userList;
    }
}
