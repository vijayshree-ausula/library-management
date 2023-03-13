package com.library.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.library.api.service.impl.MemberUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	

    @Bean
    public MemberUserDetailsService userDetailsService() {
        return new MemberUserDetailsService();
    }
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("member")
//				.password("password")
//				.roles("MEMBER")
//				.build();
//		
//		UserDetails admin = User.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("password")
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
         
        return authProvider;
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/library/api/addMember/**").permitAll();
					auth.requestMatchers("/library/api/admin/**").hasRole("ADMIN");
					auth.requestMatchers("/library/api/member/greeting/**").hasRole("MEMBER");
				})
				.httpBasic(Customizer.withDefaults())
				.build();
	}

}
