package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Forecast;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.model.WBSBudget;

public interface IWBSBudgetJpaDAO extends JpaRepository<WBSBudget, Long>,
JpaSpecificationExecutor<WBSBudget> {
	
	//WBSBudget findByName(final String name);
	//List<WBSBudget> findWbsBudgetByTaskId (final Task task);
	WBSBudget findById(final Long id);


}
