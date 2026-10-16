package com.example.ss9.controllers;

import com.example.ss9.models.User;
import com.example.ss9.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("users")
public class UserController {

    public static final String UPLOAD_DIR = "C:/Users/PhuocNTB/Documents/img/";

    @Autowired
    private UserService us;

    @GetMapping("user-data")
    @ResponseBody
    public List<User> getUsers() {
        return us.getUserList();
    }

    @GetMapping()
    public String userPage(Model model) {
        model.addAttribute("userList", us.getUserList());
        return "user";
    }

    // Hiển thị giao diện sửa user theo PathVariable: /users/edit/{id}
    @GetMapping("/edit/{id}")
    public String editUserPage(@PathVariable("id") Integer id, Model model) {
        User user = us.findById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        return "user-edit";
    }

    // Hiển thị giao diện sửa user theo query param: /users/edit?id={id}
    @GetMapping("/edit")
    public String editUserByParam(@RequestParam("id") Integer id, Model model) {
        return editUserPage(id, model);
    }

    // Xử lý cập nhật thông tin user và lưu file avatar vào C:\Users\PhuocNTB\Documents\img
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Integer id,
                             @ModelAttribute User user,
                             @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile) throws IOException {
        user.setId(id);

        // Xử lý lưu file ảnh upload
        if (avatarFile != null && !avatarFile.isEmpty()) {
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originalFilename = avatarFile.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String uniqueFileName = UUID.randomUUID().toString() + extension;
            Path filePath = Paths.get(UPLOAD_DIR, uniqueFileName);

            Files.copy(avatarFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Đường dẫn web để hiển thị ảnh qua /images/**
            user.setAvatar("/images/" + uniqueFileName);
        } else {
            // Nếu không upload file mới và URL avatar bỏ trống thì giữ nguyên avatar cũ
            User existing = us.findById(id);
            if (existing != null && (user.getAvatar() == null || user.getAvatar().isBlank())) {
                user.setAvatar(existing.getAvatar());
            }
        }

        us.updateUser(user);
        return "redirect:/users";
    }

    // Hỗ trợ submit form POST /users/update
    @PostMapping("/update")
    public String updateUserPost(@ModelAttribute User user,
                                 @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile) throws IOException {
        if (user.getId() != null) {
            return updateUser(user.getId(), user, avatarFile);
        }
        return "redirect:/users";
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
