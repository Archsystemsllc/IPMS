package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.ModelIPMS;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;

public interface IModelService extends IService<ModelIPMS> {
	
	ModelIPMS findByName(final String name);

	List<ModelIPMS> findActiveUserModels(Principal principal);

	List<ModelIPMS> findActiveModels();

	List<ModelIPMS> findUserModels(Principal currentUser);
	
	List<ModelIPMS> findModelListByProgram(Program program);


}
