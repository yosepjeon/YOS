package com.yosep.order.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.yosep.order.entity.Order;
import com.yosep.order.repository.OrderDAO;

@RefreshScope
@Component
public class OrderComponent {
	
	@Autowired
	OrderDAO orderDAO;
	
	public List<Order> orderList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void createOrder(Order order) {
		// TODO Auto-generated method stub
		orderDAO.createOrder(order);
	}

	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		
	}
}
