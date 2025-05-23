    package com.project.Lyricys.Config;

    import com.project.Lyricys.Repositories.UserRepository;
    import com.project.Lyricys.Services.JwtService;
    import com.project.Lyricys.Security.JwtAuthenticationFilter;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig {
        private final JwtService jwtService;
        private final UserDetailsService userDetailsService;

        public WebSecurityConfig(JwtService jwtService, UserDetailsService userDetailsService) {
            this.jwtService = jwtService;
            this.userDetailsService = userDetailsService;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                            .requestMatchers("/error").permitAll()
                            .anyRequest().authenticated()
                    )

                    .addFilterBefore(
                            new JwtAuthenticationFilter(jwtService, userDetailsService),
                            org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class
                    );
            return http.build();
        }
    }
