package spring.config;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ruokit.main.common.aop.NameMatchClassMethodPointcut;
import com.ruokit.main.common.aop.logging.DaoLoggingAdvice;
import com.ruokit.main.common.aop.logging.ServiceLoggingAdvice;

@Configuration
public class RuokitLoggingConfig {

  /*
   * Service Logging
   */
  @Bean
  public ServiceLoggingAdvice serviceLoggingAdvice() {
    return new ServiceLoggingAdvice();
  }

  @Bean
  public NameMatchClassMethodPointcut serviceLoggingPotinCut() {
    NameMatchClassMethodPointcut pointcut = new NameMatchClassMethodPointcut();
    pointcut.setMappedClassName("*ServiceImpl*");
    pointcut.setMappedNames("*");
    return pointcut;
  }

  @Bean
  public DefaultPointcutAdvisor serviceLoggingAdvisor() {
    DefaultPointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor();
    pointcutAdvisor.setAdvice(serviceLoggingAdvice());
    pointcutAdvisor.setPointcut(serviceLoggingPotinCut());
    return pointcutAdvisor;
  }

  /*
   * Data Access Object Logging
   */
  @Bean
  public DaoLoggingAdvice daoLoggingAdvice() {
    return new DaoLoggingAdvice();
  }

  @Bean
  public NameMatchClassMethodPointcut daoLoggingPotinCut() {
    NameMatchClassMethodPointcut pointcut = new NameMatchClassMethodPointcut();
    pointcut.setMappedClassName("*DaoImpl*");
    pointcut.setMappedNames("*");
    return pointcut;
  }

  @Bean
  public DefaultPointcutAdvisor daoLoggingAdvisor() {
    DefaultPointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor();
    pointcutAdvisor.setAdvice(daoLoggingAdvice());
    pointcutAdvisor.setPointcut(daoLoggingPotinCut());
    return pointcutAdvisor;
  }
}
