package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.ipms.sec.model.FinancialHeader;

public interface IFinancialHeaderJpaDAO extends JpaRepository< FinancialHeader, Long >, JpaSpecificationExecutor< FinancialHeader >{

		
	@Query("select h from FinancialHeader h where h.project.id=:projectId order by h.startDate ")
	public List<FinancialHeader> findOldestHeader(@Param("projectId") Long projectId);
	

	@Query("select h from FinancialHeader h where h.project.id=:projectId order by h.endDate desc")
	public List<FinancialHeader> findRecentHeader(@Param("projectId") Long projectId);
}
