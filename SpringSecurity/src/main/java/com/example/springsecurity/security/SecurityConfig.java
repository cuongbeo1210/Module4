package com.example.springsecurity.security;

import com.example.springsecurity.filer.CustomerAuthenticationFilter;
import com.example.springsecurity.filer.CustomerAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@CrossOrigin("*")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomerAuthenticationFilter customerAuthenticationFilter = new CustomerAuthenticationFilter(authenticationManagerBean());
        customerAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(POST,"/api/login").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/users").hasAnyAuthority("User");
        http.authorizeRequests().antMatchers(POST, "/api/user/save/**").hasAnyAuthority("Admin");
        http.authorizeRequests().antMatchers(GET, "/api/product/**").hasAnyAuthority("Admin", "User");
        http.authorizeRequests().antMatchers("/api/product/admin/**").hasAnyAuthority("Admin");
        http.authorizeRequests().anyRequest().authenticated();
        http.cors().disable();
        http.addFilter(customerAuthenticationFilter);
        http.addFilterBefore(new CustomerAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
