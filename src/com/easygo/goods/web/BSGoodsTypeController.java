package com.easygo.goods.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("bsGoodsType")
@RequestMapping("/bsGoodsType")
public class BSGoodsTypeController {
	@RequestMapping("index.do")
	public ModelAndView index(){
		return new ModelAndView("goods/bsGoodsType/index");
	}
	
	public ModelAndView goodsTypeGrid(
			@RequestParam(required=false) Integer pageSize,
			@RequestParam(required=false) Integer pageNum,
			@RequestParam(required=false) String typeName){
		Map result = new HashMap<String, Object>();
		
		//TODO: result里放分页的goodsType数据
		
		
		return new ModelAndView("", result);
	}
	
}
