package spring;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import spring.config.RuokitBeanPostProcConfig;
import spring.config.RuokitDatabaseConfig;
import spring.config.RuokitLoggingConfig;
import spring.config.RuokitSecurityWebConfig;
import spring.config.RuokitWebConfig;

public class RuokitWebApplicationInitializer implements WebApplicationInitializer {

  private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    registerDispatcherServlet(servletContext);
  }

  private void registerDispatcherServlet(ServletContext servletContext) {
    // Filter
    FilterRegistration.Dynamic characterEncodingFilterRegist =
        servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
    characterEncodingFilterRegist.setInitParameter("encoding", "UTF-8");
    characterEncodingFilterRegist.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true,
        "/*");

    // Filter
    FilterRegistration.Dynamic delegatingFilterRegist =
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
    delegatingFilterRegist.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

    // Servlet
    AnnotationConfigWebApplicationContext dispatcherContext =
        new AnnotationConfigWebApplicationContext();
    dispatcherContext.register(RuokitWebConfig.class);
    dispatcherContext.register(RuokitSecurityWebConfig.class);
    dispatcherContext.register(RuokitDatabaseConfig.class);
    dispatcherContext.register(RuokitBeanPostProcConfig.class);
    dispatcherContext.register(RuokitLoggingConfig.class);

    ServletRegistration.Dynamic dispatcherServletRegist;
    dispatcherServletRegist = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
        new DispatcherServlet(dispatcherContext));
    dispatcherServletRegist.setLoadOnStartup(1);
    dispatcherServletRegist.addMapping("/");
  }
}
