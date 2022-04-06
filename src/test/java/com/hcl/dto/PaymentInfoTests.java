package com.hcl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hcl.dto.PaymentInfo;

@SpringBootTest
public class PaymentInfoTests {
	
	
	@Test
	public void createPaymentInfo () {
		
		
		
		PaymentInfo paymentInfo = new PaymentInfo();
		
		paymentInfo.setAmount(100);
		paymentInfo.setCurrency("USD");
		paymentInfo.setReceiptEmail("abc@email.com");
		
		assertEquals(100, paymentInfo.getAmount());
		assertEquals("USD", paymentInfo.getCurrency());
		assertEquals("abc@email.com", paymentInfo.getReceiptEmail());
		
	}
	
	@Test
	public void testToString() {
			
		PaymentInfo paymentInfo = new PaymentInfo();
		
		
		assertFalse(paymentInfo.toString().isEmpty());
		
	}
	
	@Test
	public void testHashCode() {
			
		PaymentInfo paymentInfo = new PaymentInfo();
		
		Object hashcode = paymentInfo.hashCode();
		
		assertEquals(hashcode,paymentInfo.hashCode());
		
	}
	
	@Test
	public void testEquals() {
			
		PaymentInfo paymentInfo = new PaymentInfo();
		PaymentInfo paymentInfo2 = new PaymentInfo();
		
		paymentInfo.equals(paymentInfo2);
		paymentInfo2.equals(paymentInfo);
		
		
		
		
	}
	
	

}
