package com.yosep.order.repository;

import java.util.List;

import com.yosep.order.entity.Order;

public interface OrderDAO {
	// 01. 주문 목록
	public List<Order> orderList(String userId);
	
	// 02. 주문 생성
	public void createOrder(Order order);
	
	// 03. 주문 업데이트
	public void updateOrder(Order order);
}
