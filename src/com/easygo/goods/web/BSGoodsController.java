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

@Controller("bsGoods")
@RequestMapping("/bsGoods")
public class BSGoodsController {
	@Autowired
	private GoodsService goods;
	
	@RequestMapping("/index.do")
	public ModelAndView index(){
		return new ModelAndView("goods/bsGoods/index");
	}
	
	@RequestMapping("/goodsGrid.do")
	public ModelAndView goodsGrid(
			@RequestParam(required=false) Integer pageSize,
			@RequestParam(required=false) Integer pageNum,
			@RequestParam(required=false) String goodsName,
			@RequestParam(required=false) Integer typeId,
			@RequestParam(required=false) Double minPrice,
			@RequestParam(required=false) Double maxPrice){
		Map result = new HashMap<String, Object>();
		
		//TODO: result里放分页的goods数据
		SearchResult<Map> rs = goods.searchAppGoods(goodsName, typeId, minPrice, maxPrice, pageSize, pageNum);
		result.put("pageObject", rs);
		
		return new ModelAndView("", result);
	}
	
	@RequestMapping("/addOrEditGoods.do")
	public void addOrEditGoods(
			@RequestParam(required=false) String goodsId,
			@RequestParam String goodsName,
			@RequestParam Double price,
			@RequestParam Integer quantity,
			@RequestParam String description,
			@RequestParam Integer typeId){
		
		//TODO: 掉用service层方法,goodsId非空时修改商品,goodsId空时新增商品
		goods.addOrEditAppGoods(goodsId, goodsName, price, quantity, description, typeId);
	}
	
	@RequestMapping("/batchDelGoods.do")
	public void batchDelGoods(
			@RequestParam String[] goodsIds){
		
		//TODO: 调用service层方法,删除对应goodsId的商品
		goods.deleteAppGoods(goodsIds);
	}

	
}
