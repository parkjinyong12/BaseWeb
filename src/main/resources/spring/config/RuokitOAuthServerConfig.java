package spring.config;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@EnableAuthorizationServer
@Configuration
public class RuokitOAuthServerConfig extends AuthorizationServerConfigurerAdapter {

  @Resource(name = "jndiDataSource")
  DataSource dataSource;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    // security.passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    /*
     * ApplicationContext context = new
     * AnnotationConfigApplicationContext(RuokitAuthServerConfig.class); DataSource jndiDataSource =
     * (DataSource) context.getBean("jndiDataSource"); clients.jdbc(jndiDataSource);
     * ((AbstractApplicationContext) context).close();
     */
    clients.jdbc(dataSource);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {}

}
