package com.archsystemsinc.ipms.persistence.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Program_;

public final class ProgramSpecifications {

	private ProgramSpecifications() {
		throw new AssertionError();
	}

	// API
	public static Specification<Program> isActivePrograms() {
		return new Specification<Program>() {
			@Override
			public final Predicate toPredicate(final Root<Program> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(Program_.active), true);
			}
		};
	}

	public static Specification<Program> userPrograms(final Principal principal) {
		return new Specification<Program>() {
			@Override
			public final Predicate toPredicate(final Root<Program> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(Program_.manager), principal);
			}
		};
	}
}