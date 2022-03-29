package com.hcl.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcl.dao.CustomerRepository;
import com.hcl.dto.PaymentInfo;
import com.hcl.dto.Purchase;
import com.hcl.dto.PurchaseResponse;
import com.hcl.entity.Customer;
import com.hcl.entity.Order;
import com.hcl.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
public class CheckoutServiceImpl implements CheckoutService{
	
	private CustomerRepository customerRepository;
	
	
	// inject customer Repo into the Customer service
	public CheckoutServiceImpl(CustomerRepository customerRepository,
								@Value("${stripe.key.secret}") String secretKey) {
		
		this.customerRepository = customerRepository;
		
		// initialize Stripe API with the secret key
		Stripe.apiKey = secretKey;
		
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

	@Override
	public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
		
		// create list of payment types. They have multiple different ex. debit, wechat etc
		List<String> paymentMethodTypes = new ArrayList<>();
		
		paymentMethodTypes.add("card");
		
		
		// hash map for params for the payment 
		Map<String, Object> params = new HashMap<>();
		
		params.put("amount", paymentInfo.getAmount());
		params.put("currency", paymentInfo.getCurrency());
		params.put("payment_method_types", paymentMethodTypes);
		params.put("description", "Luv2Shop purchase");
		params.put("receipt_email", paymentInfo.getReceiptEmail());
		
		return PaymentIntent.create(params);
	}

}
