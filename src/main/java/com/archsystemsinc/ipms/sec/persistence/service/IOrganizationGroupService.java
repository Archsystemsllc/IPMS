package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;

public interface IOrganizationGroupService extends IService<OrganizationGroup> {

	OrganizationGroup findByName(final String name);

}
