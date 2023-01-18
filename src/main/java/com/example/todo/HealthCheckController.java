package com.example.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 서버 동작 확인용 on 루트 디렉토리

@RestController
@Slf4j
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<?> check() {
        log.info("Server is running...");
        return ResponseEntity
                .ok()
                .body("Server is running...");
    }
}
