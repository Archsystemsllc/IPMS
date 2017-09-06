package com.archsystemsinc.ipms.sec.persistence.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.ipms.sec.model.FinancialExpenses;

public interface IFinancialExpensesJpaDAO extends JpaRepository< FinancialExpenses, Long >, JpaSpecificationExecutor< FinancialExpenses >{

	
	@Query("select e from FinancialExpenses e where e.financialHeader.project.id=:projectId and e.reportingDate>=:startDate and e.reportingDate<=:endDate ")
	public List<FinancialExpenses> findByProjectAndPeriods(
			@Param("projectId") Long projectId,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	}
