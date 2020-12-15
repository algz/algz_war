package com.algz.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CxfClient {

	/**
	 * 通过CXF方式调用WebService接口 示例: Object
	 * obj=CxfClient.getResult("http://127.0.0.1:8080/services/Test?wsdl",
	 * "execute", new Object[]{"a","b"});
	 * 
	 * @param webServiceUrl WebService接口地址
	 * @param method        方法名称
	 * @param params        参数
	 * @return
	 */
	public static Object getResult(String webServiceUrl, String method, Object[] params) {
		// 创建CXF客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(webServiceUrl);
		try {
			return client.invoke(method, params)[0];
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
