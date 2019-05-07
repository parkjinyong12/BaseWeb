package com.ruokit.springcontext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.ruokit.controller")
public class TestSecurityWebConfig implements WebMvcConfigurer {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/res/**");
	}

	public void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	            .antMatchers("/user/**").access("ROLE_USER")
	            .antMatchers("/admin/**").access("ROLE_ADMIN")
	            .antMatchers("/", "/login", "/login-error").permitAll()
	            .antMatchers("/**").authenticated();

        http.csrf().disable();

        http.formLogin()
                .loginPage("/")
                .loginPage("/login")
                .loginProcessingUrl("/login-processing")
                .failureUrl("/login-error")
                .defaultSuccessUrl("/home", true)
                .usernameParameter("id")
                .passwordParameter("password");

	    http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true);

	    http.userDetailsService(userDetailsService);
	    //http.authenticationProvider(authenticationProvider);
	}
	
	@Bean
    public UserDetailsService userDetailsService() throws Exception {
		//return userDetailsService.loadUserByUsername(null);
		
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());       
        return manager;
    }
	
}
