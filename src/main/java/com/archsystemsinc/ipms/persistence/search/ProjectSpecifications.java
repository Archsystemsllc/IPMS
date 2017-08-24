package com.archsystemsinc.ipms.persistence.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Project_;

public final class ProjectSpecifications {

	private ProjectSpecifications() {
		throw new AssertionError();
	}

	// API
	public static Specification<Project> isActiveProjects() {
		return new Specification<Project>() {
			@Override
			public final Predicate toPredicate(final Root<Project> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(Project_.active), true);
			}
		};
	}

	public static Specification<Project> userProjects(final Principal principal) {
		return new Specification<Project>() {
			@Override
			public final Predicate toPredicate(final Root<Project> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(Project_.manager), principal);
			}
		};
	}

}
