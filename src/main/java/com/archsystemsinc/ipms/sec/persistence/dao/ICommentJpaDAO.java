package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Comment;

public interface ICommentJpaDAO extends JpaRepository<Comment, Long>,
		JpaSpecificationExecutor<Comment> {

	Comment findByName(final String name);

}
