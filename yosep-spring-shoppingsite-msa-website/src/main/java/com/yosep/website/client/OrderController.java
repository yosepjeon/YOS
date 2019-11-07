package com.yosep.website.client;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.yosep.website.entity.Order;
import com.yosep.website.entity.Product;

@CrossOrigin
@Controller
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/order/{type}/{detailType}/{productId}")
	public ModelAndView showOrderPage(@PathVariable("type") String type, @PathVariable("detailType") String detailType,
			@PathVariable("productId") String productId, HttpSession session) {
		Product product = restTemplate.getForObject(
				"http://product-apigateway/api/product/getProductDetail?productId=" + productId, Product.class);
//		System.out.println(session.getAttribute("userId"));
//		System.out.println(session.getAttribute("userName"));
		
		Order order = new Order();
		order.setBuyerId((String)session.getAttribute("userId"));
		order.setSenderName((String)session.getAttribute("userName"));
		order.setProductId(product.getProductId());
		order.setBuy(false);
		
		int httpStatus = restTemplate.postForObject("http://order-apigateway/api/order/createOrder", order, int.class);
		
		ModelAndView mav = new ModelAndView();

		mav.addObject("product", product);
		mav.addObject("userName", session.getAttribute("userName"));
		mav.setViewName("checkout_cart");

		return mav;
	}

	@RequestMapping(value = "/pay/{productId}")
	public ModelAndView showPayPage(@PathVariable("productId")String productId,@RequestParam("senderId") String senderId,
			@RequestParam("senderName") String senderName, @RequestParam("receiverName") String receiverName,
			@RequestParam("phone") String phone, @RequestParam("postCode") String postCode,
			@RequestParam("roadAddr") String roadAddr, @RequestParam("jibunAddr") String jibunAddr,
			@RequestParam("extraAddr") String extraAddr, @RequestParam("detailAddr") String detailAddr) {
		ModelAndView mav = new ModelAndView();
		Product product = restTemplate.getForObject("http://product-apigateway/api/product/getProductDetail?productId=" + productId, Product.class);
//		mav.addObject("order",order);
		
		mav.addObject("product",product);
		mav.setViewName("pay");

		return mav;
	}

	@RequestMapping("/checkout_address/{productId}")
	public ModelAndView showCheckOutAddress(@PathVariable("productId")String productId,HttpSession session) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("checkout_address");
		mav.addObject("productId",productId);
		mav.addObject("userName", session.getAttribute("userName"));
		return mav;
	}
}
