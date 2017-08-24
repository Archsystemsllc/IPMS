package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Risk;

public interface IRiskService extends IService<Risk> {

	Risk findByName(final String name);

	List<Risk> findCurrentUserRisks(Principal principal);

	List<Risk> findCurrentRisks();

}
