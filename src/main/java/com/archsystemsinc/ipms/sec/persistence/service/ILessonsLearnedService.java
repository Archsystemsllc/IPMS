package com.archsystemsinc.ipms.sec.persistence.service;


import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.LessonsLearned;

public interface ILessonsLearnedService extends IService<LessonsLearned> {

	LessonsLearned findByName(final String name);
	LessonsLearned findById(final Long id);

}
