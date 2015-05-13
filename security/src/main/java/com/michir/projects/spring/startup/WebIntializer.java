package com.michir.projects.spring.startup;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebIntializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebMvcConfig.class);
		ctx.setServletContext(servletContext);

		Dynamic dynamicServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx)); // @Important note the ctx passed to Servlet
		dynamicServlet.addMapping("/spring/*");
		dynamicServlet.setLoadOnStartup(1);
	}
}
