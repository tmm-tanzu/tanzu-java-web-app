package com.example.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthController {

    private volatile boolean health = true;

    @GetMapping("/health")
    public ResponseEntity<Void> index() {
        if (health) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping("/health/down")
    public void down() {
        this.health = false;
    }

    @PostMapping("/health/up")
    public void up() {
        this.health = true;
    }
}
