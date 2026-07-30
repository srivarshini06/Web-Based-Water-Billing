package com.water.backend.config;

import com.water.backend.entity.User;
import com.water.backend.enums.UserRole;
import com.water.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init() {
        return args -> {

            if (userRepository.findByRole(UserRole.SUPERADMIN).isEmpty()) {

                User admin = new User();
                admin.setFullName("Super Admin");
                admin.setEmail("super@admin.com");
                admin.setPhoneNumber("9999999999");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole(UserRole.SUPERADMIN);
                admin.setStatus(User.ApprovalStatus.APPROVED);

                userRepository.save(admin);

                System.out.println("🔥 SUPERADMIN CREATED");
            }
        };
    }
}