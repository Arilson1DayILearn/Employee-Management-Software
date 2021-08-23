package com.software.employeemanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.software.employeemanager.security.ApplicationUserPermissions.*;
import static com.software.employeemanager.security.ApplicationUserRols.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecuritConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecuritConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/css/*", "/js/*", "/swagger-ui.html" ).permitAll()
                .antMatchers(HttpMethod.POST, "/employee/add/**").hasAuthority(EMPLOYEE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/employee/update/**").hasAuthority(EMPLOYEE_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/employee/delete/**").hasAuthority(EMPLOYEE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/employee/all/**").hasAnyRole(ADMIN.name(),EMPLOYEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails arilsonalmadaUser = User.builder()
                .username("arilsonalmada")
                .password(passwordEncoder.encode("123456"))
                //.roles(ADMIN.name()) // ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails stephaniecostaUser = User.builder()
                .username("stephaniecosta")
                .password(passwordEncoder.encode("123456"))
                //.roles(EMPLOYEE.name()) // ROLE_EMPLOYEE
                .authorities(EMPLOYEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                arilsonalmadaUser,
                stephaniecostaUser
        );
    }
}
