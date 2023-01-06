package com.algz.webservice.example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

/**
 * 接口实现
 * 
 * @author leftso
 *
 */
@WebService(
		serviceName = "CommonServiceA" ,//对外发布的服务名称.(可省略,省略后服务名是类名,不建议省略)与接口中指定的name一致
        targetNamespace = "http://webservice.algz.com/"//, //自定义的命名空间，通常使用包名反转。 (可省略)与接口中的命名空间一致,一般是接口的包名倒写
//        endpointInterface = "com.algz.webservice.CommonServiceA"// 服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口。(可省略)接口地址
)
@Component
public class CommonServiceAImp implements CommonServiceA {

    @Override
    public String sayHello(String name) {
 
        return "Hello A ," + name;
    }

}