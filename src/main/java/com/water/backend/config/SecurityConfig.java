package com.water.backend.config;

import com.water.backend.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> {
                })

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        /*
                         * ==========================================
                         * PUBLIC ENDPOINTS
                         * ==========================================
                         */

                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/api/users/register",
                                "/api/users/login",
                                "/api/communities/register"
                        ).permitAll()

                        /*
                         * ==========================================
                         * SUPERADMIN
                         * ==========================================
                         */

                        .requestMatchers(
                                "/api/admin/**"
                        ).hasAuthority("SUPERADMIN")

                        /*
                         * ==========================================
                         * COMMUNITY ENDPOINTS
                         * ==========================================
                         */

                        .requestMatchers(
                                "/api/communities/**"
                        ).hasAnyAuthority(
                                "SUPERADMIN",
                                "COMMUNITY_ADMIN"
                        )

                        /*
                         * ==========================================
                         * RESIDENT ENDPOINTS
                         * ==========================================
                         */

                        .requestMatchers(
                                "/api/residents/**"
                        ).hasAnyAuthority(
                                "SUPERADMIN",
                                "COMMUNITY_ADMIN"
                        )

                        /*
                         * ==========================================
                         * FUTURE COMMUNITY APIs
                         * ==========================================
                         */

                        .requestMatchers(
                                "/api/community/**"
                        ).hasAnyAuthority(
                                "SUPERADMIN",
                                "COMMUNITY_ADMIN"
                        )

                        /*
                         * ==========================================
                         * FUTURE RESIDENT APIs
                         * ==========================================
                         */

                        .requestMatchers(
                                "/api/resident/**"
                        ).hasAnyAuthority(
                                "SUPERADMIN",
                                "COMMUNITY_ADMIN",
                                "RESIDENT"
                        )

                        /*
                         * ==========================================
                         * PROCUREMENT COST
                         * ==========================================
                         *
                         * Community procurement cost is accessible
                         * to Super Admin and Community Admin.
                         */

                        .requestMatchers(
                                "/api/procurement-cost/**"
                        ).hasAnyAuthority(
                                "SUPERADMIN",
                                "COMMUNITY_ADMIN"
                        )

                        /*
                         * ==========================================
                         * EVERYTHING ELSE
                         * ==========================================
                         */

                        .anyRequest().authenticated()
                )

                /*
                 * ==========================================
                 * JWT FILTER
                 * ==========================================
                 */

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
}