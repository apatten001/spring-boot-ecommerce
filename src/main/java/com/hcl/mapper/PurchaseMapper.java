package com.hcl.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.hcl.dto.PurchaseDto;
import com.hcl.entity.Purchase;

@Mapper
public interface PurchaseMapper {
	
	PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);
	
	
	PurchaseDto purchaseToPurchaseDto(Purchase purchase);
	
	Purchase purchaseDtoToPurchase(PurchaseDto purchaseDto);
	
	
	
	

}
