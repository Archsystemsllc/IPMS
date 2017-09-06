package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Forecast;
import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Task;

public interface IForecastJpaDAO extends JpaRepository<Forecast, Long>,
JpaSpecificationExecutor<Forecast> {
	
//	Forecast findByName(final String name);
	List<Forecast> findForeCastByProjectId(final Project project);

}
