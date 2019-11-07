package com.yosep.order.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yosep.order.entity.Order;

@Repository
public class OrderDAOImpl implements OrderDAO{
	static private final String NAMESPACE = "com.yosep.order.mapper.orderMapper";

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<Order> orderList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		
	}
	
}
