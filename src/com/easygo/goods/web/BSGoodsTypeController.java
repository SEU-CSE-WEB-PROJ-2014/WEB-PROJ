package com.easygo.goods.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.utils.dao.SearchResult;
import com.easygo.common.utils.platform.Platform;
import com.easygo.goods.bo.AppGoodsType;
import com.easygo.goods.service.GoodsService;
import com.easygo.goods.service.GoodsTypeService;

@Controller("bsGoodsType")
@RequestMapping("/bsGoodsType")
public class BSGoodsTypeController {	
	@Autowired
	private GoodsTypeService goodsType;
	
	@RequestMapping("/index.do")
	public ModelAndView index(){
		return new ModelAndView("goods/bsGoodsType/index");
	}
	
	@RequestMapping("/goodsTypeGrid.do")
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
	
	@RequestMapping("/AddOrEditgoodsType.do")
	public void addOrEditGoodsType(
			@RequestParam(required=false) Integer typeId,
			@RequestParam String typeName,
			@RequestParam String typeIntro){
		
		//TODO: 掉用service层方法,typeId非空时修改类型名称,typeId空时新增类型
		this.goodsType.AddOrEditAppGoodsType(typeId, typeName, typeIntro);
		
	}
	
	
	@RequestMapping("/AddOrEditgoodsTypePage.do")
	public ModelAndView addOrEditGoodsTypePage(
			@RequestParam(required=false) Integer typeId){
		Map result = new HashMap<String, Object>();
		if(typeId != null){
			AppGoodsType type = goodsType.getGoodsType(typeId);
			result.put("type", type);
		}
		
		return new ModelAndView("goods/bsGoodsType/addOrEditGoodsType", result);
	}
	
	
	@RequestMapping("/delGoodsType.do")
	public void delGoodsType(
			@RequestParam Integer typeId){
		
		//TODO: 调用service层方法,删除对应typeId的类型
		goodsType.DeleteAppGoodsType(new Integer[]{typeId});
	}
	
}
