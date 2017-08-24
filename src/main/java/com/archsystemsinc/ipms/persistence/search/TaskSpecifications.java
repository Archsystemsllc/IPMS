package com.archsystemsinc.ipms.persistence.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.model.TaskStatus;
import com.archsystemsinc.ipms.sec.model.Task_;

public final class TaskSpecifications {

	private TaskSpecifications() {
		throw new AssertionError();
	}

	// API
	public static Specification<Task> notClosedTasks() {
		return new Specification<Task>() {
			@Override
			public final Predicate toPredicate(final Root<Task> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				final Predicate notClosed = builder.notEqual(
						root.get(Task_.status),
 TaskStatus.Closed.toString());
				return notClosed;
			}
		};
	}

	public static Specification<Task> notResolvedTasks() {
		return new Specification<Task>() {
			@Override
			public final Predicate toPredicate(final Root<Task> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				final Predicate notResolved = builder.notEqual(
						root.get(Task_.status), TaskStatus.Resolved.toString());
				return notResolved;

			}
		};
	}

	public static Specification<Task> userTasks(final Principal principal) {
		return new Specification<Task>() {
			@Override
			public final Predicate toPredicate(final Root<Task> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(Task_.assignedTo), principal);
			}
		};
	}

}
