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
//@WebService(
//		serviceName = "CommonServiceB" // (可省略,省略后服务名是类名,不建议省略)与接口中指定的name一致
//        targetNamespace = "http://webservice.leftso.com/", // (可省略)与接口中的命名空间一致,一般是接口的包名倒写
//        endpointInterface = "com.algz.webservice.CommonServiceB"// (可省略)接口地址
//)
@Component
public class CommonServiceBImp implements CommonServiceB {

    @Override
    public String sayHello(String name) {
 
        return "Hello B ," + name;
    }

}