package com.hcl.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;


import com.hcl.entity.Product;

// specify type of Entity and its Id type for ProductRepo its Product and Long 
// For CORS(Cross origin resource sharing) error this is the port Angular is running on
// Origin = the protocol + hostname + port

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product,Long> {
	
	// query methods to match by categroy id and filter by request param value id
	// this is similar to SELECT * FROM product WHERE category_id=?
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
	
	
	// Select * FROM Product p WHERE p.name Like  CONCAT('%',:name,'%')
	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
	

}
