package com.archsystemsinc.ipms.spring.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan( "com.archsystemsinc.ipms.security" )
@ImportResource( { "classpath*:springSecurityConfig.xml" } )
public class SecurityConfig{
	
	public SecurityConfig(){
		super();
	}
	
}
