package com.archsystemsinc.ipms.spring.testing;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( { "com.archsystemsinc.ipms.testing", "com.archsystemsinc.ipms.sec.testing" } )
public class TestingConfig{
	//
}
