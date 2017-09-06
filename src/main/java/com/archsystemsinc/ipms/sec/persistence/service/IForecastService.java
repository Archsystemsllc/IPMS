package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Forecast;
import com.archsystemsinc.ipms.sec.model.Project;

public interface IForecastService extends IService<Forecast> {
	List<Forecast> findForeCastByProjectId(Project project);

}
