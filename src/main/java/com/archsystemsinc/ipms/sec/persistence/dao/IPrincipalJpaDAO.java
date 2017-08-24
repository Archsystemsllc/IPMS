package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Principal;

public interface IPrincipalJpaDAO extends JpaRepository< Principal, Long >, JpaSpecificationExecutor< Principal >{
	
	Principal findByName( final String name );
	
}
