package com.archsystemsinc.ipms.sec.persistence.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.ipms.sec.model.FinancialExpenses;
import com.archsystemsinc.ipms.sec.model.FinancialHours;

public interface IFinancialHoursJpaDAO extends JpaRepository< FinancialHours, Long >, JpaSpecificationExecutor< FinancialExpenses >{

	
	@Query("select h from FinancialHours h where h.financialHeader.project.id=:projectId and h.reportingDate>=:startDate and h.reportingDate<=:endDate ")
	public List<FinancialHours> findByProjectAndPeriods(
			@Param("projectId") Long projectId,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
}
