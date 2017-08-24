package com.archsystemsinc.ipms.sec.persistence.service;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Role;

public interface IRoleService extends IService< Role >{
	
	Role findByName( final String name );
	
}
