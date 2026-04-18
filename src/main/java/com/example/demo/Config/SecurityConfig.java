package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 👥 USER + ADMIN
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password("{noop}user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/login", "/css/**", "/js/**").permitAll()

                //USER e ADMIN possono SOLO LEGGERE e CREARE
                .requestMatchers(
                        "/",
                        "/findAllManga",
                        "/mangaForm",
                        "/fumetteriaForm",
                        "/mangaSalvato",
                        "/fumetteriaSalvata"
                ).hasAnyRole("USER", "ADMIN")

                //SOLO ADMIN può modificare ed eliminare
                .requestMatchers(
                        "/aggiornaManga/**",
                        "/aggiornaFumetteria/**",
                        "/eliminaManga/**",
                        "/eliminaFumetteria/**"
                ).hasRole("ADMIN")

                .anyRequest().authenticated()
            )

            //LOGIN
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )

            //LOGOUT
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
            )

            .httpBasic(withDefaults());

        return http.build();
    }
}