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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ruokit, spring.aop")
public class RuokitWebConfig implements WebMvcConfigurer {

  private static final Logger logger = LoggerFactory.getLogger(RuokitWebConfig.class);
  private static final String appPropPath = "/config/application.properties";

  private String[] environmentTypes = {"dev", "prod"};

  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/view/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("redirect:/main.do");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }

  @Bean
  public Properties appConfiguration() throws Exception {

    String commonPropPath = "";
    String dbPropPath = "";
    Properties properties = new Properties();

    InputStream applicationProperties = null;
    InputStream commProperties = null;
    InputStream dbProperties = null;

    try {

      applicationProperties = getClass().getResourceAsStream(appPropPath);
      properties.load(new BufferedInputStream(applicationProperties));

      // 개발환경 체크
      String environment = properties.getProperty("environment");
      boolean environmentCheck = false;
      if (environment == null) {
        logger.error("Exception : environment setting is null");
        throw new Exception();
      } else {
        for (String type : environmentTypes) {
          if (type.equals(environment)) {
            logger.info("environment : " + environment);
            environmentCheck = true;
          }
        }
        if (!environmentCheck) {
          logger.error("environment : " + environment);
          logger.error("Exception : environment type is unknown");
          throw new Exception();
        }
      }
      commonPropPath = "/config/" + environment + "/common.properties";
      dbPropPath = "/config/" + environment + "/db.properties";

      commProperties = getClass().getResourceAsStream(commonPropPath);
      dbProperties = getClass().getResourceAsStream(dbPropPath);

      properties.load(new BufferedInputStream(commProperties));
      logger.info("commonProperties version : " + properties.getProperty("version") + ", classpath:"
          + commonPropPath);
      properties.load(new BufferedInputStream(dbProperties));
      logger.info("dbProperties version : " + properties.getProperty("version") + ", classpath:"
          + dbPropPath);

    } catch (IOException e1) {
      logger.error("Exception : properties file loading");

    } finally {
      if (commProperties != null) {
        try {
          commProperties.close();
        } catch (IOException e) {
          logger.error("Exception : commProperties inputStream close");
        }
      }
      if (dbProperties != null) {
        try {
          dbProperties.close();
        } catch (IOException e) {
          logger.error("Exception : dbProperties inputStream close");
        }
      }
    }
    return properties;
  }
}
