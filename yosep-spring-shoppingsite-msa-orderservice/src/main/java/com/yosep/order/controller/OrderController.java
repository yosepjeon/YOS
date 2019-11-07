package com.yosep.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yosep.order.component.OrderComponent;
import com.yosep.order.entity.Order;

@RestController
@CrossOrigin
@RequestMapping(value="/order")
public class OrderController {
	@Autowired
	private OrderComponent orderComponent;
	
	@RequestMapping(value="/createOrder",method=RequestMethod.POST)
	public int createOrder(@RequestBody Order order) {
		orderComponent.createOrder(order);
		
		return 200;
	}
	
	@RequestMapping(value="/updateOrder",method=RequestMethod.POST)
	public int updateOrder() {
		return -1;
	}
}
