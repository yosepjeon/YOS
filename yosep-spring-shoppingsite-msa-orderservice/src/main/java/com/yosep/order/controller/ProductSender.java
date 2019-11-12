package com.yosep.order.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ProductSender {
	RabbitMessagingTemplate template;
	
	@Autowired
	public ProductSender(RabbitMessagingTemplate template) {
		// TODO Auto-generated constructor stub
		this.template = template;
	}
	
	@Bean
	Queue queue() {
		return new Queue("ProductQ", false);
	}
	
	public void send(String productId) {
		System.out.println("send productId!!!");
		template.convertAndSend("productId",productId);
	}
}
