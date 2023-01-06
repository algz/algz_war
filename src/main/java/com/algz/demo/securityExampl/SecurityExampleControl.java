package com.algz.demo.securityExampl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/securityexample")
public class SecurityExampleControl {

	@RequestMapping("test")
	public String testExample() {
		return "ok";
	}
	
	
}
