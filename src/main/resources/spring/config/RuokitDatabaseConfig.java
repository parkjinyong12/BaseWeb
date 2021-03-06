package spring.config;

import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class RuokitDatabaseConfig {

  // private static final Logger logger = LoggerFactory.getLogger(TestDatabaseConfig.class);

  @Resource(name = "appConfiguration")
  private Properties properties;

  @Bean
  public DataSource jndiDataSource() {
    JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
    jndiDataSourceLookup.setResourceRef(true);
    return jndiDataSourceLookup.getDataSource("jdbc/test");
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(ApplicationContext applicationContext)
      throws Exception {

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(jndiDataSource());
    sqlSessionFactoryBean.setConfigLocation(
        applicationContext.getResource(properties.getProperty("db.mybatis.config")));
    sqlSessionFactoryBean.setMapperLocations(
        applicationContext.getResources(properties.getProperty("db.mybatis.mapper.location")));
    sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(ApplicationContext applicationContext)
      throws Exception {
    SqlSessionTemplate sqlSessionTemplate =
        new SqlSessionTemplate(sqlSessionFactory(applicationContext));
    return sqlSessionTemplate;
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(jndiDataSource());
    return transactionManager;
  }
}
