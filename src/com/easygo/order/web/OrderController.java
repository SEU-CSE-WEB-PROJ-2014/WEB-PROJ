package com.easygo.order.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.utils.BusinessException;
import com.easygo.common.utils.dao.SearchResult;
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
	public ModelAndView myOrder(
			@RequestParam(required=false) Integer payState,
			@RequestParam(required=false) Integer transState,
			@RequestParam(required=false) Integer signState,
			@RequestParam(required=false) Integer pageSize,
			@RequestParam(required=false) Integer pageNum){
		String userId = UserManager.getCurrentUserId();
		if(StringUtils.isEmpty(userId)){
			throw new BusinessException("您尚未登录");
		}
		
		Map result = new HashMap<String, Object>();
		SearchResult<Map> sr = this.orderService.searchAppOrder(payState, transState, signState, pageSize, pageNum);
		result.put("pageObject", sr);
		return new ModelAndView("order/orderList", result);
	}
	
	@RequestMapping("/myOrderPage.do")
	public ModelAndView myOrderPage(){
		String userId = UserManager.getCurrentUserId();
		if(StringUtils.isEmpty(userId)){
			throw new BusinessException("您尚未登录");
		}
		Map result = new HashMap<String, Object>();
		SearchResult<Map> sr = this.orderService.searchAppOrder(null, null, null, null, null);
		result.put("pageObject", sr);
		return new ModelAndView("order/myOrder", result);
	}
	
	@RequestMapping("/setOrderTransState.do")
	public void setOrderTransState(
			@RequestParam String orderId,
			@RequestParam Integer transState){
		
		//TODO: 掉用service层方法,设置订单发货状态
		orderService.setOrderTransState(orderId, transState);
	}
	
	@RequestMapping("/batchCancelOrder.do")
	public void batchCancelOrder(
			@RequestParam String[] orders){
		
		orderService.batchCancelOrder(orders);
	}
	
    @RequestMapping("/addOrder.do")//获取商品信息，检查库存余量，库存减去购买数量，计算总价，数据库update商品信息，增加订单，保存数据库
    public void addOrder(
    		@RequestParam String goodsId,
    		@RequestParam String userId,
    		@RequestParam Integer quantity
    		){
    	orderService.addOrder(goodsId, userId, quantity);
    	
    }
    
    @RequestMapping("/payOrder.do")
    public void payOrder(@RequestParam String orderId){
    	orderService.payOrder(orderId);
    }
    
    
    @RequestMapping("/signOrder.do")
    public void signOrder(@RequestParam String orderId){
    	orderService.signOrder(orderId);
    }
}
