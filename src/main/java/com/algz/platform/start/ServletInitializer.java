package com.algz.platform.start;

import javax.servlet.ServletContext;

//import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
//		 return new ServletRegistrationBean(new CXFServlet(), "/kexin/webservice/*");
		return application.sources(AlgzWarApplication.class);
	}

	
//	@Override
//	public void onStartup(ServletContext servletContext) {
//		servletContext.addServlet("CXFServlet", new CXFServlet()).addMapping("/kexin/webservice/*");
//	}
}
