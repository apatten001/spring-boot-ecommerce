package com.hcl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// protect the endpoint for api/orders
		
		http.authorizeRequests()
				.antMatchers("/api/orders/**") // protects the endpoint only accessible to authenticated
				.authenticated()
				.and()
				.oauth2ResourceServer() // configure Oauth2 Resource server support
				.jwt(); // enables JWT encoded bearer token support
		
		// add support for the cors filters	
		http.cors();
		
		// force a non-empty response for 401s
		
		Okta.configureResourceServer401ResponseBody(http);
		
		// disable CSRF since we are not using cookies for session tracking
		http.csrf().disable();
		
		
	}
	
	

}
