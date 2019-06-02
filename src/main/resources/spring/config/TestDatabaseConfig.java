package spring.config;

import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class TestDatabaseConfig {

  private static final Logger logger = LoggerFactory.getLogger(TestDatabaseConfig.class);
  
  @Resource(name="appConfiguration")
  private Properties properties;
  
  @Bean
  public DataSource jndiDataSource() {
    JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
    jndiDataSourceLookup.setResourceRef(true);
    return jndiDataSourceLookup.getDataSource("jdbc/test");
  }
 
  @Bean 
  public SqlSessionFactory sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
	 	
	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(jndiDataSource());

    sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(properties.getProperty("db.mybatis.config")));
	sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(properties.getProperty("db.mybatis.mapper.location")));
		
    return sqlSessionFactoryBean.getObject();
  }


  @Bean
  public SqlSessionTemplate sqlSession(ApplicationContext applicationContext) throws Exception {
    SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory(applicationContext));
    return sqlSessionTemplate;
  }
  
  @Bean
  public JtaTransactionManager transactionManager() {
    JtaTransactionManager transactionManager = new JtaTransactionManager();
    return transactionManager;
  }
}
