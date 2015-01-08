package com.easygo.order.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easygo.common.utils.BusinessException;
import com.easygo.common.utils.userManager.UserManager;
import com.easygo.order.service.AppOrderService;


@Controller("order")
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private AppOrderService orderService;
	
	@RequestMapping("/order.do")
	public void order(@RequestParam String goodsId, @RequestParam Integer quantity){
		String userId = UserManager.getCurrentUserId();
		
		if(StringUtils.isEmpty(userId)){
			throw new BusinessException("您尚未登录");
		}
		
		orderService.addOrder(goodsId, userId, quantity);
	}
	
	
	@RequestMapping("/myOrder.do")
	public void myOrder(){
		String userId = UserManager.getCurrentUserId();
		if(StringUtils.isEmpty(userId)){
			throw new BusinessException("您尚未登录");
		}
		
		
	}
}
