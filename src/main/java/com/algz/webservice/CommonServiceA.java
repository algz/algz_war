package com.algz.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(
//name = "CommonServiceA", // 暴露服务名称
//targetNamespace = "http://service.webservice.kexin.com"// 命名空间,一般是接口的包名倒序
)
public interface CommonServiceA  {
//	@WebMethod
//    @WebResult(name = "String", targetNamespace = "")
    public String sayHello(@WebParam(name = "userName") String name);
}
