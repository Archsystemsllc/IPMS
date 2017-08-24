package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.MeetingPrincipal;
import com.archsystemsinc.ipms.sec.model.RevisionHistory;

public interface IRevisionHistoryService extends IService<RevisionHistory> {

	RevisionHistory findByName(final String name);
	
	List<RevisionHistory> findByRequirement(final Long requirementId);
	
	void bulkCreate(Iterable<RevisionHistory> itms);
	
	List<RevisionHistory> findByIssue(final Long issueId);

	List<RevisionHistory> findByTask(final Long taskId);
	
	List<RevisionHistory> findByActionItem(final Long actionItemId);
	
	List<RevisionHistory> findByRisk(final Long riskId);
	
	List<RevisionHistory> findByLessonsLearned(final Long lessonsLearnedId);

}
