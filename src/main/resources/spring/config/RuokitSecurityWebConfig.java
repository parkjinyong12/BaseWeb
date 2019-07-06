package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.ruokit.main.common.login.LoginFailureHandler;
import com.ruokit.main.common.login.LoginSuccessHandler;

/**
 * Spring Security 인증(Authentication) 처리를 진행합니다.
 * 
 * @author jinyong.park
 */
@Configuration
@EnableWebSecurity
public class RuokitSecurityWebConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * 전역 보안 설정. 리소스 무시 등등
   */
  @Override
  public void configure(WebSecurity auth) throws Exception {
    auth.ignoring().antMatchers("/mail/**", "/test/**");
  }

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

    http.authorizeRequests().antMatchers("/login.do").permitAll().antMatchers("/**")// .access("100").antMatchers("/**").access("200").antMatchers("/**")
        .authenticated();

    http.csrf().disable();

    http.formLogin().loginPage("/login.do").loginProcessingUrl("/j_spring_security_check.do")
        .usernameParameter("j_username").passwordParameter("j_password")
        .defaultSuccessUrl("/", true).successHandler(new LoginSuccessHandler())
        .failureHandler(new LoginFailureHandler());

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
    daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
    return daoAuthenticationProvider;
  }
}
