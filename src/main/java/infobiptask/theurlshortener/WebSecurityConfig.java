package infobiptask.theurlshortener;


import infobiptask.theurlshortener.store.IUrlStoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AppBasicAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private IUrlStoreService urlStoreService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {

		//		Map<String, String> accounts = urlStoreService.getAccounts();
		//		for (Map.Entry<String, String> entry : accounts.entrySet()) {
		//	        String id = entry.getKey();
		//	        String pass = entry.getValue();
		//	        auth.inMemoryAuthentication().withUser(id).password(pass)
		//			.authorities("ROLE_USER").and;
		//	    }

		auth.inMemoryAuthentication()
.withUser("aldin").password("{noop}aldin")
		.authorities("ROLE_USER");

	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/account")
		.permitAll().anyRequest().authenticated().and().httpBasic()
		.authenticationEntryPoint(authenticationEntryPoint);
	}
}
