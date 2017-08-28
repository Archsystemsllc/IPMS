package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.ModelIPMS;
import com.archsystemsinc.ipms.sec.model.Program;

public interface IModelJpaDAO extends JpaRepository<ModelIPMS, Long>,
JpaSpecificationExecutor<ModelIPMS> {
	ModelIPMS findByName(final String name);
	
	List<ModelIPMS> findModelListByProgram(final Program program);
}


