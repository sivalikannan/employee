package com.emp1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
						.requestMatchers(new AntPathRequestMatcher("/employees/new")).hasAnyRole("ADMIN", "EMPLOYEE")
						.requestMatchers(new AntPathRequestMatcher("/employees", "POST")).hasAnyRole("ADMIN", "EMPLOYEE")
						.requestMatchers(new AntPathRequestMatcher("/employees/*/edit")).hasRole("ADMIN")
						.requestMatchers(new AntPathRequestMatcher("/employees/*", "POST")).hasRole("ADMIN")
						.requestMatchers(new AntPathRequestMatcher("/employees/*/delete", "POST")).hasRole("ADMIN")
						.requestMatchers(new AntPathRequestMatcher("/employees/**")).hasAnyRole("ADMIN", "EMPLOYEE")
						.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/employees", true)
						.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/login?logout")
						.permitAll())
				.httpBasic(Customizer.withDefaults())
				.build();
	}

	@Bean
	UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.builder()
				.username("admin")
				.password(encoder.encode("admin123"))
				.roles("ADMIN")
				.build();

		UserDetails employee = User.builder()
				.username("employee")
				.password(encoder.encode("emp123"))
				.roles("EMPLOYEE")
				.build();

		return new InMemoryUserDetailsManager(admin, employee);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

