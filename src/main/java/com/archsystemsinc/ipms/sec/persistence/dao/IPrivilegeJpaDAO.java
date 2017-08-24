package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Privilege;

public interface IPrivilegeJpaDAO extends JpaRepository< Privilege, Long >, JpaSpecificationExecutor< Privilege >{
	
	Privilege findByName( final String name );
	
}
