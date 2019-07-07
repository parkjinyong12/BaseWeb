package spring.config;

import javax.annotation.Resource;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.ruokit.main.common.aop.NameMatchClassMethodPointcut;
import com.ruokit.main.common.aop.logging.DaoLoggingAdvice;
import com.ruokit.main.common.aop.logging.ServiceLoggingAdvice;
import com.ruokit.main.common.aop.transaction.TransactionAdvice;

@Configuration
public class RuokitAspectOrientedConfig {

  @Resource(name = "transactionManager")
  DataSourceTransactionManager dataSourceTransactionManager;

  /*
   * Transaction Manage
   */
  @Bean
  public TransactionAdvice transactionAdvice() {
    TransactionAdvice txAdvice = new TransactionAdvice();
    txAdvice.setTransactionManager(dataSourceTransactionManager);
    return txAdvice;
  }

  @Bean
  public NameMatchClassMethodPointcut transactionPotinCut() {
    NameMatchClassMethodPointcut pointcut = new NameMatchClassMethodPointcut();
    pointcut.setMappedClassName("*ServiceImpl*");
    pointcut.setMappedNames("*");
    return pointcut;
  }

  @Bean
  public DefaultPointcutAdvisor transactionAdvisor() {
    DefaultPointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor();
    pointcutAdvisor.setAdvice(transactionAdvice());
    pointcutAdvisor.setPointcut(transactionPotinCut());
    return pointcutAdvisor;
  }

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
