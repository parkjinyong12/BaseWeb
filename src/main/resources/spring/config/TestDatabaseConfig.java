package spring.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;

@PropertySource("classpath:mybatis/db.properties")
@Configuration
public class TestDatabaseConfig {

  @Autowired
  private Environment environment;

  @Autowired
  private ApplicationContext applicationContext;

  private static final Logger logger = LoggerFactory.getLogger(TestDatabaseConfig.class);

  @Bean
  public JndiObjectFactoryBean dataSource() {
    // DriverManagerDataSource source = new DriverManagerDataSource();
    // source.setDriverClassName(environment.getProperty("dbDriverClass"));
    // source.setUrl(environment.getProperty("dbUrl"));
    // source.setUsername(environment.getProperty("dbUser"));
    // source.setPassword(environment.getProperty("dbPassword"));

    JndiObjectFactoryBean source = new JndiObjectFactoryBean();
    source.setJndiName("jdbc/test");
    source.setResourceRef(true);

    return source;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    // sqlSessionFactoryBean.setDataSource(dataSource());
    sqlSessionFactoryBean.setConfigLocation(
        applicationContext.getResource(environment.getProperty("mybatisConfig")));
    sqlSessionFactoryBean.setMapperLocations(
        applicationContext.getResources(environment.getProperty("mybatisMapperXML")));

    logger.debug(environment.getProperty("mybatisConfig"));
    logger.debug(environment.getProperty("mybatisMapperXML"));
    return sqlSessionFactoryBean.getObject();
  }


  @Bean
  public SqlSession sqlSession() throws Exception {
    SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
    return sqlSessionTemplate;
  }
}
