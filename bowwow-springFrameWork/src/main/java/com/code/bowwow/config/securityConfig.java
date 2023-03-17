package com.code.bowwow.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.code.bowwow.service.bowwowService;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{	
		auth.jdbcAuthentication()
				.dataSource(securityDataSource)
				.usersByUsernameQuery("SELECT email, password, 'true' AS enabled FROM user WHERE email=?")  //username:email , enabled:true
				.authoritiesByUsernameQuery("SELECT email, grade FROM user WHERE email=?")  // 권한부여
				;

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
    			.antMatchers("/css/**").permitAll()  //css파일 모든접근 권한 부여
    			.antMatchers("/images/**").permitAll() //이미지파일 모든접근 권한 부여
	    		.antMatchers("/", "/home","/signup_form","/signup").permitAll()   // home페이지, 회원가입페이지 로그인없이 접근 가능
	    		.antMatchers("/vip").hasAnyRole("VIP","ADMIN")
	    		.antMatchers("/master_form").hasRole("ADMIN")
	    		.anyRequest().authenticated()
	    		.and()
	        .formLogin()
	        	.loginPage("/login")
	            .loginProcessingUrl("/authenticateTheUser")
	            .permitAll()
	            .and() 
            .logout().permitAll()
	            .and()
            .exceptionHandling().accessDeniedPage("/access-denide");
	           
	    
	    
	    CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
	           
	}
}
