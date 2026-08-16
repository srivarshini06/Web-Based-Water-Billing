package com.water.backend.controller;

import com.water.backend.service.UserService; import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension; import org.springframework.security.core.Authentication; import static org.mockito.Mockito.*; import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class) class UserControllerTest { @Mock UserService service; @Mock Authentication auth; @InjectMocks UserController controller; @Test void currentUserUsesAuthenticationName(){when(auth.getName()).thenReturn("user@example.com");assertEquals("Welcome user@example.com",controller.currentUser(auth).getBody());}}
