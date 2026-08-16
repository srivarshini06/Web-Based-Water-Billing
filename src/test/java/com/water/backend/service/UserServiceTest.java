package com.water.backend.service;

import com.water.backend.dto.request.UserProfileUpdateRequest;
import com.water.backend.entity.User;
import com.water.backend.exception.ResourceAlreadyExistsException;
import com.water.backend.repository.UserRepository;
import com.water.backend.security.JwtService;
import com.water.backend.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) class UserServiceTest {
 @Mock UserRepository userRepository; @Mock PasswordEncoder passwordEncoder; @Mock JwtService jwtService; @InjectMocks UserServiceImpl service;
 @Test void updateProfileUpdatesNameAndPhone(){User u=User.builder().userId(1L).email("a@a.com").fullName("Old").phoneNumber("111").build(); when(userRepository.findByEmail("a@a.com")).thenReturn(Optional.of(u)); when(userRepository.save(u)).thenReturn(u); var r=service.updateProfile("a@a.com",new UserProfileUpdateRequest("New","222")); assertEquals("New",r.getFullName()); verify(userRepository).save(u);}
 @Test void duplicatePhoneIsRejected(){User u=User.builder().email("a@a.com").fullName("Old").phoneNumber("111").build(); when(userRepository.findByEmail("a@a.com")).thenReturn(Optional.of(u)); when(userRepository.existsByPhoneNumber("222")).thenReturn(true); assertThrows(ResourceAlreadyExistsException.class,()->service.updateProfile("a@a.com",new UserProfileUpdateRequest("New","222")));}
}
