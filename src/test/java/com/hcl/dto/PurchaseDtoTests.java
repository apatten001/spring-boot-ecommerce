package com.hcl.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.dto.PurchaseDto;
import com.hcl.entity.Address;
import com.hcl.entity.Customer;
import com.hcl.entity.Order;
import com.hcl.entity.OrderItem;

@SpringBootTest
public class PurchaseDtoTests {
	
	
	@Test
	public void purchaseTest() {
		
		PurchaseDto purchase = new PurchaseDto();
		
		PurchaseDto purchase2 = purchase;
		
		purchase.equals(purchase2);
		
		Address address = new Address();
		
		address.setZipCode("");
		address.setCity("");
		address.setCountry("");
		address.setId((long)1);
		address.setState("");
		address.setStreet("");
		
		Order order = new Order();
		
		Customer customer = new Customer();
		
		Set<OrderItem> orderItems = new HashSet<OrderItem>();
		
		Set<Order> orders = new HashSet<Order>();
		
		customer.setEmail("");
		customer.setFirstName("arnold");
		customer.setLastName("Pat");
		customer.setId((long)1);
		customer.setOrders(orders);

		
		purchase.setBillingAddress(address);
		purchase.setShippingAddress(address);
		purchase.setCustomer(customer);
		purchase.setOrder(order);
		purchase.setOrderItems(orderItems);
		
		assertEquals(address, purchase.getBillingAddress());
		assertEquals(address, purchase.getShippingAddress());
		assertEquals(customer, purchase.getCustomer());
		assertEquals(order, purchase.getOrder());
		assertEquals(orderItems, purchase.getOrderItems());
		
		
		
		System.out.println(purchase.toString());
		System.out.println(purchase.hashCode());
		
		

	}

}
