package com.algz.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/demo1")
public class DemoControl {

	@RequestMapping("index1")
	public ModelAndView getIndex1() {
		 ModelAndView mv=new ModelAndView();
	        mv.setViewName("test");
	        mv.addObject("name", "liyafei");
//	        user.setAge(20);
//	        user.setName("wangwu");
//	        mv.addObject("user", user);
	        
	        //设置返回的数据为json类型，也可以不设置，返回对象
	        //mv.setView(new MappingJackson2JsonView());
	        return mv; 
	}
	
	@RequestMapping("index2")
	public String getIndex2() {
		return "test";
	}
}
