package com.easygo.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller("bsOrder")
@RequestMapping("/bsOrder")
public class BSOrderController {
	@RequestMapping("/index.do")
	public ModelAndView index(){
		return new ModelAndView("order/bsOrder/index");
	}

}
