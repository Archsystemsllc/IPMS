package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Role;

public interface IRoleJpaDAO extends JpaRepository< Role, Long >, JpaSpecificationExecutor< Role >{
	
	Role findByName( final String name );
	
}
