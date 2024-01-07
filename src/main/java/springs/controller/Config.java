package springs.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authorize -> authorize
	           .requestMatchers("/dashboard").authenticated()
	           .anyRequest().permitAll()
	            ).
		formLogin(auth -> {
			try {
				auth.loginPage("/loginPage").and().httpBasic();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		
		return http.build();
	}

}
