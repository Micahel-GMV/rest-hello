package com.example.resthello;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.example.resthello.RequestLogger.LogLevel.*;

@RestController
public class GreetingController {
    @GetMapping("/greet")
    public ResponseEntity<?> greet(@RequestParam(name = "name", required = true) String name, HttpServletRequest request) {
        String response = "{\"answer\": \"Hello " + name + "!\"}";
        RequestLogger.logRequest(this.getClass(), request, INFO);
        return ResponseEntity.ok(response);
    }
}
