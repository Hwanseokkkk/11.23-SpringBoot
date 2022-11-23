package kr.kwangan2.springbootsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import kr.kwangan2.springbootsecurity.service.impl.BoardUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BoardUserDetailsService boardUserDetailsService;

	@Override
	protected void configure(HttpSecurity security) throws Exception {

		security.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/member").authenticated()
				.antMatchers("/manager").hasRole("MANAGER")
				.antMatchers("/admin").hasRole("ADMIN");

		security.csrf().disable();
		
		security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true);
		
		security.exceptionHandling().accessDeniedPage("/accessDenied");
		
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
		
		security.userDetailsService(boardUserDetailsService);
	}//configure
	
	}//class
