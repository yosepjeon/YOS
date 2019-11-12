package com.yosep.product.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.yosep.product.repository.ProductDAO;

@Component
public class ProductReceiver {
	@Autowired
	ProductDAO productDAO;
	
	@Bean
	Queue queue() {
		return new Queue("ProductQ", false);
	}
	
	@RabbitListener(queues="ProductQ")
	public void processMessage(String productId) {
		System.out.println("구매 메시지 도착");
		productDAO.setProductQuantity(productId);
	}
}
