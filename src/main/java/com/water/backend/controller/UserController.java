package com.water.backend.controller;

import com.water.backend.dto.request.LoginRequest;
import com.water.backend.dto.request.UserRequest;
import com.water.backend.dto.response.LoginResponse;
import com.water.backend.dto.response.UserResponse;
import com.water.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ✅ REGISTER
    @PostMapping("/users/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody UserRequest request) {
        return userService.register(request);
    }

    // ✅ LOGIN
    @PostMapping("/users/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(userService.login(request));
    }

    // ✅ CURRENT USER
    @GetMapping("/users/me")
    public ResponseEntity<String> currentUser(Authentication authentication) {
        return ResponseEntity.ok("Welcome " + authentication.getName());
    }

    // 🔥 SUPERADMIN APPROVE (CLEAN VERSION)
    @PutMapping("/admin/approve/{id}")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<UserResponse> approveUser(@PathVariable Long id) {

        return ResponseEntity.ok(userService.approveUser(id));
    }

    // ❌ REJECT USER
    @PutMapping("/admin/reject/{id}")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<UserResponse> rejectUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.rejectUser(id));
    }

    // ❌ SUSPEND USER
    @PutMapping("/admin/suspend/{id}")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<UserResponse> suspendUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.suspendUser(id));
    }
}