package com.hcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hcl.entity.ProductCategory;

// productCategory is the name of the JSON entry : product-category is the path 
@RepositoryRestResource(collectionResourceRel = "productCategory", path="product-category")
<<<<<<< HEAD
@CrossOrigin("http://localhost:4200")
=======
>>>>>>> bee5ea46e54964eafe6b30a2cadf7452c5ff05d7
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
