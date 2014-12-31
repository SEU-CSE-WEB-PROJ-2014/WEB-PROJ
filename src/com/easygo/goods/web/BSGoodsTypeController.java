package com.easygo.goods.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.utils.dao.SearchResult;
import com.easygo.goods.service.GoodsService;
import com.easygo.goods.service.GoodsTypeService;

@Controller("bsGoodsType")
@RequestMapping("/bsGoodsType")
public class BSGoodsTypeController {	
	@Autowired
	private GoodsTypeService goodsType;
	
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
		SearchResult<Map> rs = goodsType.SearchAppGoodsType(typeName, pageSize, pageNum);
		result.put("pageObject", rs);
		
		return new ModelAndView("", result);
	}
	
}
