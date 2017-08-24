package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.ProjectMileStone;

public interface IProjectMileStoneService extends IService<ProjectMileStone> {

	ProjectMileStone findByName(final String name);

	List<ProjectMileStone> findByProject(final Project project);
}
