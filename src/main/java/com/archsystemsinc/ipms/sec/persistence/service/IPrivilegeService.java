package com.archsystemsinc.ipms.sec.persistence.service;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Privilege;

public interface IPrivilegeService extends IService< Privilege >{
	
	Privilege findByName( final String name );
	
}
