package com.archsystemsinc.ipms.persistence.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.archsystemsinc.ipms.sec.model.ModelIPMS;
import com.archsystemsinc.ipms.sec.model.Model_;
import com.archsystemsinc.ipms.sec.model.Principal;

public final class ModelSpecifications {
	private ModelSpecifications() {
		throw new AssertionError();
	}
	// API
		public static Specification<ModelIPMS> isActiveModels() {
			return new Specification<ModelIPMS>() {
				@Override
				public final Predicate toPredicate(final Root<ModelIPMS> root,
						final CriteriaQuery<?> query, final CriteriaBuilder builder) {
					return builder.equal(root.get(Model_.active), true);
				}
			};
		}

		public static Specification<ModelIPMS> userModels(final Principal principal) {
			return new Specification<ModelIPMS>() {
				@Override
				public final Predicate toPredicate(final Root<ModelIPMS> root,
						final CriteriaQuery<?> query, final CriteriaBuilder builder) {
					return builder.equal(root.get(Model_.manager), principal);
				}
			};
		}

}
