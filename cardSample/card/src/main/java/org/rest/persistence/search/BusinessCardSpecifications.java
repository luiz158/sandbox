package org.rest.persistence.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.rest.sec.model.BusinessCard;
import org.springframework.data.jpa.domain.Specification;

public final class BusinessCardSpecifications {

    private BusinessCardSpecifications() {
        throw new AssertionError();
    }

    // API

    public static Specification<BusinessCard> lastNameIsLike(final String searchTerm) {
        return new Specification<BusinessCard>() {
            @Override
            public final Predicate toPredicate(final Root<BusinessCard> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
                // return builder.equal( root.get, y );
                return null;
            }
        };
    }

}
