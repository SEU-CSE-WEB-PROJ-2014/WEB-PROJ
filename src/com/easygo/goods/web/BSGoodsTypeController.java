package com.easygo.goods.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bsGoodsType")
@RequestMapping("/bsGoodsType")
public class BSGoodsTypeController {
	@RequestMapping("index.do")
	public ModelAndView index(){
		return new ModelAndView("goods/bsGoodsType/index");
	}
	
	
	
}
