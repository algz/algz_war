package com.algz.demo;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CxfClient {
	
    public static void main(String args[]) throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf.createClient("http://localhost:9090/services/hello?wsdl");
        Object[] objects = client.invoke("sayHello", "wangsong");
        // 输出调用结果  *****hello wangsong
        System.out.println("*****" + objects[0].toString());
    }
	


//    /**
//     * 1.代理类工厂的方式,需要拿到对方的接口地址
//     */
//    public static void main1() {
//        try {
//            // 接口地址
//            String address = "http://127.0.0.1:8080/soap/user?wsdl";
//            // 代理工厂
//            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//            // 设置代理地址
//            jaxWsProxyFactoryBean.setAddress(address);
//            // 设置接口类型
//            jaxWsProxyFactoryBean.setServiceClass(UserService.class);
//            // 创建一个代理接口实现
//            UserService us = (UserService) jaxWsProxyFactoryBean.create();
//            // 数据准备
//            String userId = "maple";
//            // 调用代理接口的方法调用并返回结果
//            String result = us.getUserName(userId);
//            System.out.println("返回结果:" + result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 2：动态调用
     */
    public static void main2() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://127.0.0.1:8080/soap/user?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("getUserName", "maple");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
