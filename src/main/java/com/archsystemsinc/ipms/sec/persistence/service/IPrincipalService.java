package com.archsystemsinc.ipms.sec.persistence.service;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Principal;

public interface IPrincipalService extends IService< Principal >{

	Principal findByName( final String name );

}
