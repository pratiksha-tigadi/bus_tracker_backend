package com.tracker.bus_tracker.controller;

import com.tracker.bus_tracker.entity.AuthUser;
import com.tracker.bus_tracker.repository.AuthUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000*")
@RestController
@AllArgsConstructor
public class UserController {

    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public LoginResponse login(@RequestParam String username, @RequestParam String password) {
        Optional<AuthUser> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent() && passwordEncoder.matches(password,userOpt.get().getPassword())) {
            return new LoginResponse(true, "Login successful");
        } else {
            return new LoginResponse(false, "Invalid username or password");
        }
    }
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody AuthUser user){
        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken. Please try again.");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/printUsers")
    public ResponseEntity<List<AuthUser>> printUsers() {
        List<AuthUser> users = userRepository.findAll();
        for (AuthUser user : users) {
            System.out.println("User: " + user.getUsername() + ", Password: " + user.getPassword());
        }
        return ResponseEntity.ok(users);
    }
}
