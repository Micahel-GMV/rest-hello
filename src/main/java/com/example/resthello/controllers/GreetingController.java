package com.example.resthello.controllers;

import com.example.resthello.RequestLogger;
import com.example.resthello.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.resthello.RequestLogger.LogLevel.INFO;

@RestController
public class GreetingController {

    @Autowired
    private RequestLogger requestLogger;
    @Autowired
    private UserService userService;
    @GetMapping("/greet")
    public ResponseEntity<?> greet(@RequestParam(name = "name", required = true) String name, HttpServletRequest request) {
        String responseString = "{\"answer\": \"Hello " + name + "!\"}";
        userService.saveUser(name);
        ResponseEntity response = ResponseEntity.ok(responseString);
        requestLogger.logRequest(this.getClass(), request, response, INFO);
        return response;
    }
}
