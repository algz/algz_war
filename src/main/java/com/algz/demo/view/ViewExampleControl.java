package com.algz.demo.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/viewexample")
public class ViewExampleControl {

	
	public ModelAndView testView() {
	     ModelAndView mv=new ModelAndView();
	        mv.setViewName("index");
	        mv.addObject("name", "liyafei");
//	        user.setAge(20);
//	        user.setName("wangwu");
//	        mv.addObject("user", user);
	        
	        //设置返回的数据为json类型，也可以不设置，返回对象
	        //mv.setView(new MappingJackson2JsonView());
	        return mv;
	}
}
