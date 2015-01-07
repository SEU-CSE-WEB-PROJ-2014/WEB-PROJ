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

@Controller("goods")
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsTypeService typeService;
	
	@RequestMapping("/searchGoods.do")
	public ModelAndView searchGoods(){
		Map result = new HashMap<String, Object>();
		Map[] types = this.typeService.SearchAppGoodsType(null, Integer.MAX_VALUE, 0).toArray();
		SearchResult<Map> sr = this.goodsService.searchAppGoods(null, null, null, null, null, null);
		
		result.put("pageObject", sr);
		result.put("types", types);
		return new ModelAndView("goods/searchGoods", result);
	}
	
	@RequestMapping("/goodsList.do")
	public ModelAndView goodsList(
			@RequestParam(required=false) Integer pageSize,
			@RequestParam(required=false) Integer pageNum,
			@RequestParam(required=false) String goodsName,
			@RequestParam(required=false) Integer typeId,
			@RequestParam(required=false) Double minPrice,
			@RequestParam(required=false) Double maxPrice){
		Map result = new HashMap<String, Object>();
		SearchResult<Map> sr = goodsService.searchAppGoods(goodsName, typeId, minPrice, maxPrice, pageSize, pageNum);
		
		result.put("pageObject", sr);
		return new ModelAndView("goods/goodsList", result);
	}
}
