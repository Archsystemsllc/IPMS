package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.Principal;

public interface IActionItemService extends IService<ActionItem> {

	ActionItem findByName(final String name);

	List<ActionItem> findCurrentUserActionItems(Principal principal);

	List<ActionItem> findCurrentActionItems();
	
	//List<ActionItem> findCurrentUserActionItems(Meeting meeting);
}
