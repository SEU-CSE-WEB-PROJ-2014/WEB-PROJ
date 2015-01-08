package com.easygo.goods.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.utils.BusinessException;
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
	public ModelAndView searchGoods(
			@RequestParam(required=false) Integer pageSize,
			@RequestParam(required=false) Integer pageNum,
			@RequestParam(required=false) String goodsName,
			@RequestParam(required=false) Integer typeId,
			@RequestParam(required=false) Double minPrice,
			@RequestParam(required=false) Double maxPrice){
		Map result = new HashMap<String, Object>();
		if(StringUtils.isEmpty(goodsName)){
			goodsName = null;
		}
		
		Map[] types = this.typeService.SearchAppGoodsType(null, Integer.MAX_VALUE, 0).toArray();
		SearchResult<Map> sr = goodsService.searchAppGoods(goodsName, typeId, minPrice, maxPrice, pageSize, pageNum);
		result.put("types", types);
		
		result.put("pageObject", sr);
		result.put("goodsName", goodsName);
		result.put("typeId", typeId);
		result.put("minPrice", minPrice);
		result.put("maxPrice", maxPrice);
		
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
	
	
	@RequestMapping("/browseGoods.do")
	public ModelAndView browseGoods(@RequestParam String goodsId){
		Map result = new HashMap<String, Object>();
		Map[] goodsInfo = this.goodsService.browseGoods(goodsId).toArray();
		if(ArrayUtils.isEmpty(goodsInfo)){
			throw new BusinessException("您访问的商品不存在或已下架");
		}
		result.put("goods", goodsInfo[0]);
		
		return new ModelAndView("goods/goodsDetail", result);
	}
}
