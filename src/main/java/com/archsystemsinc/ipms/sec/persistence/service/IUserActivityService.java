package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.UserActivity;

public interface IUserActivityService extends IService<UserActivity> {
	
	List<UserActivity> findByEntityType(String type);

	List<UserActivity> findByEntityTypeInOrderByDateCreatedDesc(List<String> entityTypes);
}
