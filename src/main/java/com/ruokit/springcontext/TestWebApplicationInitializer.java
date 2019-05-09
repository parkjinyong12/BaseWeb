package com.ruokit.springcontext;

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

public class TestWebApplicationInitializer implements WebApplicationInitializer {

  private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
  // private static final String SECURITY_SERVLET_NAME = "security";

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    // servletContext.setInitParameter("key", "value");
    registerDispatcherServlet(servletContext);
  }

  private void registerDispatcherServlet(ServletContext servletContext) {

    // 리스너 추가
    // AnnotationConfigWebApplicationContext listenerContext = new
    // AnnotationConfigWebApplicationContext();
    // listenerContext.register(TestListenerConfig.class);
    // servletContext.addListener(new ContextLoaderListener(listenerContext));

    // 필터 추가
    FilterRegistration.Dynamic characterEncodingFilterRegist =
        servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
    characterEncodingFilterRegist.setInitParameter("encoding", "UTF-8");
    characterEncodingFilterRegist.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true,
        "/*");

    // 필터 추가
    FilterRegistration.Dynamic delegatingFilterRegist =
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
    delegatingFilterRegist.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

    // 서블릿 추가
    AnnotationConfigWebApplicationContext dispatcherContext =
        new AnnotationConfigWebApplicationContext();
    dispatcherContext.register(TestWebConfig.class);
    dispatcherContext.register(TestSecurityWebConfig.class);

    ServletRegistration.Dynamic dispatcherServletRegist;
    dispatcherServletRegist = servletContext.addServlet(DISPATCHER_SERVLET_NAME,
        new DispatcherServlet(dispatcherContext));
    dispatcherServletRegist.setLoadOnStartup(1);
    dispatcherServletRegist.addMapping("/");

    // 서블릿 추가
    // AnnotationConfigWebApplicationContext securityContext = new
    // AnnotationConfigWebApplicationContext();
    // securityContext.register(TestSecurityWebConfig.class);
    //
    // ServletRegistration.Dynamic securityServletRegist;
    // securityServletRegist = servletContext.addServlet(SECURITY_SERVLET_NAME, new
    // DispatcherServlet(securityContext));
    // securityServletRegist.setLoadOnStartup(1);
    // securityServletRegist.addMapping("/");
  }
}
