package com.algz.webservice;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sany.webservice.DesignPlatformService;

/**
 * @Description:cxf发布webservice配置
 * @author algz
 *
 */
@Configuration
public class CxfConfig {
    @Autowired
    private Bus bus;
 
    @Autowired
    private CommonServiceA commonServiceA;
    
    @Autowired
    private CommonServiceB commonServiceB;
    
    @Autowired
    private DesignPlatformService designPlatformService;
 
    /**
     * 
     *  spring boot2.0.6之后的版本与CXF集成，不需要定义如下方法(解决找不到dispatcherServlet的异常问题)：
	 *   换成配置的方法，在application.yml中添加：cxf.path=/service，既可以解决这个问题了。
     * 
     * 此方法作用是改变项目中服务名的前缀名，此处127.0.0.1或者localhost不能访问时，请使用ipconfig查看本机ip来访问
     * 此方法被注释后:wsdl访问地址为http://127.0.0.1:8080/services/user?wsdl
     * 去掉注释后：wsdl访问地址为：http://127.0.0.1:8080/kexin/webservice/user?wsdl
     * @return
     */
//    @Bean
//    public ServletRegistrationBean dispatcherServlet() {
//        return new ServletRegistrationBean(new CXFServlet(), "/kexin/webservice/*");
//    }
    
    /** JAX-WS (SOAP) **/
    @Bean
    public Endpoint endpointA() {
        EndpointImpl endpoint = new EndpointImpl(bus, commonServiceA);
        //http://localhost:8080/algz/services/CommonService?WSDL
        endpoint.publish("/CommonServiceA");
        return endpoint;
    }
    
    @Bean
    public Endpoint endpointB() {
        EndpointImpl endpoint = new EndpointImpl(bus, commonServiceB);
        //http://localhost:8080/algz/services/CommonService?WSDL
        endpoint.publish("/CommonServiceB");
        return endpoint;
    }
    
    @Bean
    public Endpoint endpointC() {
        EndpointImpl endpoint = new EndpointImpl(bus, designPlatformService);
        //http://localhost:8080/algz/services/DesignPlatformService?WSDL
        endpoint.publish("/DesignPlatformService");
        return endpoint;
    }
}
