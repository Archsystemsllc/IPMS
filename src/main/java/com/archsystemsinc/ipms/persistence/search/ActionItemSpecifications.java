package com.archsystemsinc.ipms.persistence.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.ActionItemStatus;
import com.archsystemsinc.ipms.sec.model.ActionItem_;
import com.archsystemsinc.ipms.sec.model.Principal;

public final class ActionItemSpecifications {

	private ActionItemSpecifications() {
		throw new AssertionError();
	}

	// API
	public static Specification<ActionItem> notClosedActionItems() {
		return new Specification<ActionItem>() {
			@Override
			public final Predicate toPredicate(final Root<ActionItem> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				final Predicate notClosed = builder.notEqual(
						root.get(ActionItem_.status),
						ActionItemStatus.Closed.toString());
				return notClosed;
			}
		};
	}

	public static Specification<ActionItem> notCompletedActionItems() {
		return new Specification<ActionItem>() {
			@Override
			public final Predicate toPredicate(final Root<ActionItem> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				final Predicate notResolved = builder.notEqual(
						root.get(ActionItem_.status),
						ActionItemStatus.Completed.toString());
				return notResolved;

			}
		};
	}

	public static Specification<ActionItem> userActionItems(
			final Principal principal) {
		return new Specification<ActionItem>() {
			@Override
			public final Predicate toPredicate(final Root<ActionItem> root,
					final CriteriaQuery<?> query, final CriteriaBuilder builder) {
				return builder.equal(root.get(ActionItem_.assignedTo),
						principal);
			}
		};
	}

}
