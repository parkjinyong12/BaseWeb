package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security 인증(Authentication) 처리를 진행합니다.
 * 
 * @author jinyong.park
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.ruokit, spring.impl")
public class TestSecurityWebConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * 로그인 인증
   */
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  /*
   * URL을 통한 접근 인증
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests().antMatchers("/login.do", "/loginFail.do", "/mail/**", "/test/**")
        .permitAll().antMatchers("/**").authenticated();

    http.csrf().disable();

    http.formLogin().loginPage("/login.do").loginProcessingUrl("/j_spring_security_check.do")
        .defaultSuccessUrl("/loginSuccess.do").failureUrl("/loginFail.do")
        .usernameParameter("j_username").passwordParameter("j_password");

    http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
        .invalidateHttpSession(true);
  }

  /*
   * 로그인 인증 설정
   */
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService); //
    // daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
    return daoAuthenticationProvider;
  }
}
