package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Principal;

public interface IIssueService extends IService<Issue> {

	Issue findByName(final String name);

	List<Issue> findCurrentUserIssues(Principal principal);

	List<Issue> findCurrentIssues();
}
