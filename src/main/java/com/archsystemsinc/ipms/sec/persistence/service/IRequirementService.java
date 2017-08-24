package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Requirement;

public interface IRequirementService extends IService<Requirement> {

	Requirement findByName(final String name);
	List<Requirement> findCurrentUserRequirements(Principal principal);

	List<Requirement> findCurrentRequirements();

	void save(Requirement requirement);

}
