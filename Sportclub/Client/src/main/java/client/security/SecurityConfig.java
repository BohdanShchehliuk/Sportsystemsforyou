package client.security;

import client.service.impl.MyUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Password Encoder
    private PasswordEncoder passwordEncoder;
    private MyUserDetailsService service;

    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(final HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("**/public/").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/register/").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/register/").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/user/**").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic();
    }
}