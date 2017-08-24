package com.archsystemsinc.ipms.persistence.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.IssueStatus;
import com.archsystemsinc.ipms.sec.model.Issue_;
import com.archsystemsinc.ipms.sec.model.Principal;

public final class IssueSpecifications {

	private IssueSpecifications() {
		throw new AssertionError();
	}

	// API
	public static Specification<Issue> notClosedIssues() {
		return new Specification<Issue>() {
			@Override
			public final Predicate toPredicate(final Root<Issue> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				final Predicate notClosed = builder.notEqual(
						root.get(Issue_.status), IssueStatus.Closed.toString());
				return notClosed;
			}
		};
	}

	public static Specification<Issue> notResolvedIssues() {
		return new Specification<Issue>() {
			@Override
			public final Predicate toPredicate(final Root<Issue> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				final Predicate notResolved = builder.notEqual(
						root.get(Issue_.status),
						IssueStatus.Resolved.toString());
				return notResolved;

			}
		};
	}

	public static Specification<Issue> userIssues(final Principal principal) {
		return new Specification<Issue>() {
			@Override
			public final Predicate toPredicate(final Root<Issue> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(Issue_.assigned), principal);
			}
		};
	}

}
