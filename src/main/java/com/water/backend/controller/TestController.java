package com.water.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // ✅ Public endpoint
    @GetMapping("/")
    public String home() {
        return "Backend is running!";
    }

    // ✅ Only SUPERADMIN
    @GetMapping("/api/superadmin")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public String superAdmin() {
        return "SUPERADMIN access granted";
    }

    // ✅ SUPERADMIN + COMMUNITY_ADMIN
    @GetMapping("/api/community")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN')")
    public String community() {
        return "COMMUNITY ADMIN access granted";
    }

    // ✅ All roles
    @GetMapping("/api/resident")
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','COMMUNITY_ADMIN','RESIDENT')")
    public String resident() {
        return "RESIDENT access granted";
    }
}