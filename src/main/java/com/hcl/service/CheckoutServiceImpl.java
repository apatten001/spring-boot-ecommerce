package com.hcl.service;


import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dao.CustomerRepository;
import com.hcl.dto.Purchase;
import com.hcl.dto.PurchaseResponse;
import com.hcl.entity.Customer;
import com.hcl.entity.Order;
import com.hcl.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService{
	
	private CustomerRepository customerRepository;
	
	
	// inject customer Repo into the Customer service
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		
		this.customerRepository = customerRepository;
		
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		// retrieve the order info from the dto
		
		Order order  = purchase.getOrder();
		
		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		// populate orders with orderItem's
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		
		
		//populate order with billing Address and shippingAddress
		// populate order with billingAddress
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		
		// populate customer with order
		
		Customer customer = purchase.getCustomer();
		
		String theEmail = customer.getEmail(); 
		Customer customerFromDB = customerRepository.findByEmail(theEmail);
		
		if (customerFromDB != null) {
			customer = customerFromDB;
		}
		
		customer.add(order);
		
		// save to the database 
		
		customerRepository.save(customer);
		
		// return a response
		return new PurchaseResponse(orderTrackingNumber);
		
		
		
		
		
	}

	private String generateOrderTrackingNumber() {
		
		// generate random UUID number 
		// (Unique Universal IDentifier) for generating unique Id's
		
		return UUID.randomUUID().toString();
	}

}
