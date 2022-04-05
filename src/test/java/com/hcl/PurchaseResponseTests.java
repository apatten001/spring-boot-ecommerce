package com.hcl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.dto.PurchaseResponse;

@SpringBootTest
public class PurchaseResponseTests{
	
	
	@Test
	public void getOrderTrackingNumberTest() {
		
		PurchaseResponse response = new PurchaseResponse("");
		
		response.getOrderTrackingNumber();
		
	}
	
	@Test
	public void equalsTest() {
		
		PurchaseResponse response = new PurchaseResponse("");
		PurchaseResponse response2 = new PurchaseResponse("");
		
		response.equals(response2);
		
		System.out.println(response.toString());
		System.out.println(response.hashCode());
		
	}

}
