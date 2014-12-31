package com.easygo.goods.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("bsGoods")
@RequestMapping("/bsGoods")
public class BSGoodsController {
	@RequestMapping("index.do")
	public ModelAndView index(){
		return new ModelAndView("goods/bsGoods/index");
	}
	
	
	
}
