package spring.config;

import java.util.Properties;

import javax.sql.DataSource;

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
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@PropertySource("classpath:mybatis/db.properties")
@Configuration
public class TestDatabaseConfig {

  private static final Logger logger = LoggerFactory.getLogger(TestDatabaseConfig.class);
  
  @Bean
  public DataSource jndiDataSource() {
    JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
    jndiDataSourceLookup.setResourceRef(true);
    return jndiDataSourceLookup.getDataSource("jdbc/test");
  }

//  @Bean
//  public DataSource dataSource() {
//    DriverManagerDataSource source = new DriverManagerDataSource();
//    source.setDriverClassName(environment.getProperty("dbDriverClass"));
//    source.setUrl(environment.getProperty("dbUrl"));
//    source.setUsername(environment.getProperty("dbUser"));
//    source.setPassword(environment.getProperty("dbPassword"));
//    return source;
//  }

  
  @Bean 
  public SqlSessionFactory sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
	  		    	  
	Properties properties = (Properties) applicationContext.getBean("getConfiguration");
	
	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(jndiDataSource());

    sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(properties.getProperty("mybatisConfig")));
	sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(properties.getProperty("mybatisMapperXML")));
		
    return sqlSessionFactoryBean.getObject();
  }


  @Bean
  public SqlSessionTemplate sqlSession(ApplicationContext applicationContext) throws Exception {
    SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory(applicationContext));
    return sqlSessionTemplate;
  }
}
