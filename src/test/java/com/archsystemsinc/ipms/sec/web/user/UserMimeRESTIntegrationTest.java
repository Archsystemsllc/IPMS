package com.archsystemsinc.ipms.sec.web.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.archsystemsinc.ipms.sec.client.template.UserRESTTemplateImpl;
import com.archsystemsinc.ipms.sec.model.dto.User;
import com.archsystemsinc.ipms.sec.test.SecMimeRESTIntegrationTest;

public class UserMimeRESTIntegrationTest extends SecMimeRESTIntegrationTest< User >{
	
	@Autowired private UserRESTTemplateImpl restTemplate;
	
	public UserMimeRESTIntegrationTest(){
		super( User.class );
	}
	
	// tests
	
	// template method
	
	@Override
	protected final UserRESTTemplateImpl getAPI(){
		return restTemplate;
	}
	
}
