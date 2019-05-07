package com.ruokit.springcontext;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class TestWebApplicationInitializer implements WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET_NAME = "BaseWeb";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {	
		//servletContext.setInitParameter("key", "value");
		registerDispatcherServlet(servletContext);		
	}
	
	private void registerDispatcherServlet(ServletContext servletContext) {
		
		// 리스너 추가		
		
		// 필터 추가
		FilterRegistration.Dynamic filterRegist = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());			
		EnumSet<DispatcherType> dispatcherType = EnumSet.of(DispatcherType.REQUEST);
		filterRegist.setInitParameter("encoding", "UTF-8");
		filterRegist.addMappingForUrlPatterns(dispatcherType, true, "/*");
		
		// 서블릿 추가
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(TestWebConfig.class);
			
		ServletRegistration.Dynamic servletRegist;		
		servletRegist = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(dispatcherContext));
		//servletRegist.setInitParameter("key", "value");
		servletRegist.setLoadOnStartup(1);
		servletRegist.addMapping("/");
	}
}
