package com.hcl.config;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
>>>>>>> bee5ea46e54964eafe6b30a2cadf7452c5ff05d7
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

<<<<<<< HEAD
import com.hcl.entity.Product;
import com.hcl.entity.ProductCategory;
=======
import com.hcl.entity.Country;
import com.hcl.entity.Order;
import com.hcl.entity.Product;
import com.hcl.entity.ProductCategory;
import com.hcl.entity.State;
>>>>>>> bee5ea46e54964eafe6b30a2cadf7452c5ff05d7

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	
<<<<<<< HEAD
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		
=======
	@Value("${allowed.origins}")
	private String[] theAllowedOrigins;
	private EntityManager entityManager;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager){
		entityManager = theEntityManager;
		
	}
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		
		
>>>>>>> bee5ea46e54964eafe6b30a2cadf7452c5ff05d7
		// These are the methods of Product that we are going to disable for our page Put Post Patch and Delete
		// We will test these using PostMan
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE,HttpMethod.PATCH};
		
<<<<<<< HEAD
		config.getExposureConfiguration()
		.forDomainType(Product.class)
		.withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
		// These are the methods of ProductCategory that we are going to disable for our page Put Post and Delete
		// We will test these using PostMan
		config.getExposureConfiguration()
		.forDomainType(ProductCategory.class)
		.withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
=======
		
		// These are the methods of the Classes that we are going to disable for our page Put Post and Delete
		// We will test these using PostMan
		disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
		disableHttpMethods(Product.class, config, theUnsupportedActions);
		disableHttpMethods(Country.class, config, theUnsupportedActions);
		disableHttpMethods(State.class, config, theUnsupportedActions);
		disableHttpMethods(Order.class, config, theUnsupportedActions);
		
		
		
		//helper method to expose ids
		exposeIds(config);
		
		//configure cors mapping
		cors.addMapping(config.getBasePath()+ "/**").allowedOrigins(theAllowedOrigins);
		
	}

	// These are the methods of the Classes that we are going to disable for our page Put Post and Delete
	private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration()
		.forDomainType(theClass)
		.withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}
	
	private void exposeIds(RepositoryRestConfiguration config) {
		
		// expose entity ids for the access of the details path
		
		// get a list of all entity classes from the entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		// create an array list of entity types
		List<Class> entityClasses = new ArrayList<>();
		
		// get the entity types for the entities
		for(EntityType tempEntityType: entities ) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		// expose the entity ids for the array of entity/domain types
		
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
>>>>>>> bee5ea46e54964eafe6b30a2cadf7452c5ff05d7
		
		
	}
	
	

}
