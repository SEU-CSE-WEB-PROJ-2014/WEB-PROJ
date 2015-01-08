package com.easygo.order.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easygo.common.utils.dao.SearchResult;
import com.easygo.order.service.AppOrderService;


@Controller("bsOrder")
@RequestMapping("/bsOrder")
public class BSOrderController {
	@Autowired
	private AppOrderService appOrder;
	
	@RequestMapping("/index.do")
	public ModelAndView index(){
		return new ModelAndView("order/bsOrder/index");
	}
	
	@RequestMapping("/orderGrid.do")
	public ModelAndView orderGrid(
			@RequestParam Integer pageSize,
			@RequestParam Integer pageNum,
			@RequestParam(required=false) Integer payState,
			@RequestParam(required=false) Integer transState,
			@RequestParam(required=false) Integer signState){
		Map result = new HashMap<String, Object>();
		
		//调用service层,返回分页用户数据:
		SearchResult<Map> rs = appOrder.searchAppOrder(payState, transState, signState, pageSize, pageNum);
		result.put("pageObject", rs);
		return new ModelAndView("", result);
	}
	
	@RequestMapping("/setOrderTransState.do")
	public void setOrderTransState(
			@RequestParam String orderId,
			@RequestParam Integer transState){
		
		//TODO: 掉用service层方法,设置订单发货状态
		appOrder.setOrderTransState(orderId, transState);
	}
	
	@RequestMapping("/batchCancelOrder.do")
	public void batchCancelOrder(
			@RequestParam String[] orders){
		
		appOrder.batchCancelOrder(orders);
	}
	
    @RequestMapping("/addOrder.do")//获取商品信息，检查库存余量，库存减去购买数量，计算总价，数据库update商品信息，增加订单，保存数据库
    public void addOrder(
    		@RequestParam String goodsId,
    		@RequestParam String userId,
    		@RequestParam Integer quantity
    		){
    	appOrder.addOrder(goodsId, userId, quantity);
    	
    }
    
    @RequestMapping("/payOrder.do")
    public void payOrder(@RequestParam String orderId){
    	appOrder.payOrder(orderId);
    }
    
    
    @RequestMapping("/signOrder.do")
    public void signOrder(@RequestParam String orderId){
    	appOrder.signOrder(orderId);
    }
    
    @RequestMapping("/deliverGoodsPage.do")
    public ModelAndView deliverGoodsPage(@RequestParam String orderId){
    	Map result = new HashMap<String, Object>();
    	result.put("orderId", orderId);
    	
    	return new ModelAndView("order/bsOrder/deliverGoods", result);
    }
    
    @RequestMapping("/deliverGoods.do")
    public void deliverGoods(@RequestParam String orderId, @RequestParam String invoiceNum){
    	appOrder.deliverGoods(orderId, invoiceNum);
    }
}

   
   
