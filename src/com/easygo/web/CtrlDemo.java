package com.easygo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CtrlDemo {
	
	@RequestMapping(value="abc.do")
	public void test(){
		System.out.println("controller test");
	}
}
