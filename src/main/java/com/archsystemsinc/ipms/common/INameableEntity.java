package com.archsystemsinc.ipms.common;

import java.util.List;
import java.util.Set;

import com.archsystemsinc.ipms.sec.model.Principal;

public interface INameableEntity extends IEntity{
	
	String getName();
	
	Principal getOwner();
	
	Set<Principal> getStakeHolders(); 
}
