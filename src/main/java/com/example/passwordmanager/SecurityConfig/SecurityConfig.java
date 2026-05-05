package com.example.passwordmanager.SecurityConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Authorization Rules: Maps URLs to required roles
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 🔐 ADMIN RULE: Protect all paths starting with /admin (CRUD operations, dashboard)
                        .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")

                        // 🔐 USER RULE: Protect the /user dashboard
                        .requestMatchers("/user").hasAnyRole("USER", "ADMIN")

                        // 🔓 PUBLIC ACCESS: Allow everyone to see the login page and static assets
                        .requestMatchers("/", "/login", "/css/**", "/js/**", "/webjars/**").permitAll()

                        // ⛔ CATCH-ALL: Ensure any other unexpected URL is locked down
                        .anyRequest().authenticated()
                )
                // 2. Form Login Configuration
                .formLogin(form -> form
                        .loginPage("/login")        // Spring uses your existing HTML login form
                        // IMPORTANT: The path below MUST be handled by Spring Security, not your controller.
                        .loginProcessingUrl("/login")
                        // Define where users go after a successful login based on their role
                        .successHandler(new CustomAuthenticationSuccessHandler())
                        .failureUrl("/login?error")
                        .permitAll()
                )
                // 3. Logout Configuration
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    // --- Temporary In-Memory User Store (To be replaced with database later) ---
    // This replaces your manual "admin123" and "user123" checks.
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // NOTE: Passwords must be ENCODED (e.g., using BCrypt)

        UserDetails admin = User.builder()
                .username("admin")
                // Use passwordEncoder.encode("admin123") to get the hash for production/DB
                .password(passwordEncoder.encode("qaadmin"))
                .roles("ADMIN", "USER") // Admin has both roles
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("qauser"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    // 4. Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Essential for security: never store or compare plain text passwords.
        return new BCryptPasswordEncoder();
    }
}