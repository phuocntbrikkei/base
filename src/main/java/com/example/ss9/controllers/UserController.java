package com.example.ss9.controllers;

import com.example.ss9.models.User;
import com.example.ss9.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService us;

    @GetMapping("user-data")
    @ResponseBody()
    public List<User> getUsers() {
        return us.getUserList();
    }

    @GetMapping()
    public String userPage(Model model) {
        model.addAttribute("userList", us.getUserList());
        return "user";
    }

    // Xóa user theo PathVariable: /users/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        us.deleteUser(id);
        return "redirect:/users";
    }

    // Xóa user theo RequestParam: /users/delete?id={id}
    @GetMapping("/delete")
    public String deleteUserByParam(@RequestParam("id") Integer id) {
        us.deleteUser(id);
        return "redirect:/users";
    }
}