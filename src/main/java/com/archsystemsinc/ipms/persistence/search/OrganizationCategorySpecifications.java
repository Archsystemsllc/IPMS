package com.archsystemsinc.ipms.persistence.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.OrganizationCategory;
import com.archsystemsinc.ipms.sec.model.OrganizationCategory_;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;

public final class OrganizationCategorySpecifications {

	private OrganizationCategorySpecifications() {
		throw new AssertionError();
	}
	
	public static Specification<OrganizationCategory> organizationCategorysForProject(final Project project) {
		return new Specification<OrganizationCategory>() {
			@Override
			public final Predicate toPredicate(final Root<OrganizationCategory> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(OrganizationCategory_.project), project);
			}
		};
	}
	
	public static Specification<OrganizationCategory> organizationCategorysForAssigned(final Principal principal) {
		return new Specification<OrganizationCategory>() {
			@Override
			public final Predicate toPredicate(final Root<OrganizationCategory> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(OrganizationCategory_.principal), principal);
			}
		};
	}


}
