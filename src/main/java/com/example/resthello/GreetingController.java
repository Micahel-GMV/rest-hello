package com.example.resthello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/greet")
    public ResponseEntity<?> greet(@RequestParam(name = "name", required = true) String name) {
        String response = "{\"answer\": \"Hello " + name + "!\"}";
        return ResponseEntity.ok(response);
    }
}
