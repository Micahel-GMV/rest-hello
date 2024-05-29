package com.example.resthello.controllers;

import com.example.resthello.db.user.User;
import com.example.resthello.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUsername(@RequestParam String name) {
        User user = userService.saveUser(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsernames() {
        List<User> usernames = userService.getAllUsernames();
        return ResponseEntity.ok(usernames);
    }
}
