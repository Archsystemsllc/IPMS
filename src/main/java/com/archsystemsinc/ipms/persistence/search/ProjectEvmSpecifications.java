package com.archsystemsinc.ipms.persistence.search;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.data.jpa.domain.Specification;


import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.ProjectEvm;
import com.archsystemsinc.ipms.sec.model.ProjectEvm_;


public final class ProjectEvmSpecifications {

	private ProjectEvmSpecifications() {
		throw new AssertionError();
	}

	// API
	public static Specification<ProjectEvm> greaterThan(final Date date) {
		return new Specification<ProjectEvm>() {
			@Override
			public final Predicate toPredicate(final Root<ProjectEvm> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.greaterThan(root.get(ProjectEvm_.date), date);
			}
		};
	}

	public static Specification<ProjectEvm> projectEvm(final Project project) {
		return new Specification<ProjectEvm>() {
			@Override
			public final Predicate toPredicate(final Root<ProjectEvm> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(ProjectEvm_.id), project.getId());
			}
		};
	}
	
	public static Specification<ProjectEvm> lessThan(final Date date) {
		return new Specification<ProjectEvm>() {
			@Override
			public final Predicate toPredicate(final Root<ProjectEvm> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.lessThan(root.get(ProjectEvm_.date), date);
			}
		};
	}

	public static Specification<ProjectEvm> forDate(final Date date) {
		return new Specification<ProjectEvm>() {
			@Override
			public final Predicate toPredicate(final Root<ProjectEvm> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(ProjectEvm_.date), date);
			}
		};
	}
}
