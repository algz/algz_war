package com.algz.platform.common.net;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algz.platform.utility.SpringSecurityUtils;

@RestController
@RequestMapping("/common/net")
public class NetControl {

	@RequestMapping("/ip")
	public String getRemoteIp(HttpServletRequest request) {
		String ip=SpringSecurityUtils.getIpAddr(request);
		return ip;
	}
}
