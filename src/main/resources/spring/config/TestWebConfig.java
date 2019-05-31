package spring.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ruokit.controller")
public class TestWebConfig {

  private static final Logger logger = LoggerFactory.getLogger(TestWebConfig.class);
  private static final String commonPropPath = "/common.properties";
  private static final String dbPropPath = "/mybatis/db.properties";
  
  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/view/");
    resolver.setSuffix(".jsp");
    return resolver;
  }
  
  @Bean(name="appConfiguration")
  public Properties appConfiguration() {
		
	Properties properties = new Properties();
	InputStream commProperties = getClass().getResourceAsStream(commonPropPath);
	InputStream dbProperties = getClass().getResourceAsStream(dbPropPath);
	
	try {
		properties.load(new BufferedInputStream(commProperties));
		logger.info("commonProperties version : " + properties.getProperty("version") + ", classpath:" + commonPropPath);
		properties.load(new BufferedInputStream(dbProperties));
		logger.info("dbProperties version : " + properties.getProperty("version") + ", classpath:" + dbPropPath);
	} catch (IOException e1) {
		logger.error("exception : properties file loading");
	} finally {
		if(commProperties != null) {
			try {
				commProperties.close();
			} catch (IOException e) {
				logger.error("exception : commProperties inputStream close");
			}
		}
		if(dbProperties != null) {
			try {
				dbProperties.close();
			} catch (IOException e) {
				logger.error("exception : dbProperties inputStream close");
			}
		}
	}	
	return properties;
  }
}
