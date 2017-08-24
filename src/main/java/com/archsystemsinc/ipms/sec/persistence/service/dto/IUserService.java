package com.archsystemsinc.ipms.sec.persistence.service.dto;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.dto.User;

public interface IUserService extends IService< User >{
	
	User findByName( final String name );
	
}
