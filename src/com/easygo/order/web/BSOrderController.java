package com.easygo.order.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller("bsOrder")
@RequestMapping("/bsOrder")
public class BSOrderController {
	@RequestMapping("/index.do")
	public ModelAndView index(){
		return new ModelAndView("order/bsOrder/index");
	}
	
	
	public ModelAndView orderGrid(
			@RequestParam Integer pageSize,
			@RequestParam Integer pageNum,
			@RequestParam(required=false) Integer payState,
			@RequestParam(required=false) Integer transState,
			@RequestParam(required=false) Integer signState){
		Map result = new HashMap<String, Object>();
		
		//调用service层,返回分页用户数据:
		
		return new ModelAndView("", result);
	}
	
	public void setOrderTransState(
			@RequestParam String orderId,
			@RequestParam Integer transState){
		
		//TODO: 掉用service层方法,设置订单发货状态
		
	}
	
	
	public void batchCancelOrder(
			@RequestParam String[] orders){
		
		//TODO: 调用service层方法,删除对应userIds的用户
		
	}

}
