package org.rest.sec.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.rest.common.persistence.model.IEntity;
import org.rest.common.search.ClientOperation;
import org.rest.common.util.QueryConstants;
import org.rest.sec.model.BusinessCard;
import org.rest.sec.model.BusinessCard_;
import org.rest.sec.model.ClientCard;
import org.rest.sec.model.ClientCard_;
import org.springframework.data.jpa.domain.Specification;

/**
 * The query specifications are built manually here; this is a limitation and should be contained to this only class
 */
@SuppressWarnings("unchecked")
public final class QuerySpecificationSec {

    private QuerySpecificationSec() {
        throw new UnsupportedOperationException();
    }

    // API - retrieve specifications for all OPs and all supported classes

    public static <T extends IEntity> Specification<T> getByNameSpecification(final Class<T> clazz, final ClientOperation op, final String name, final boolean negated) {
        switch (op) {
        case EQ:
            return getByNameSpecificationEq(clazz, name, negated);
        case NEG_EQ:
            return getByNameSpecificationEq(clazz, name, negated);

        case CONTAINS:
            return getByNameSpecificationContains(clazz, name, negated);
        case STARTS_WITH:
            return getByNameSpecificationStartsWith(clazz, name, negated);
        case ENDS_WITH:
            return getByNameSpecificationEndsWith(clazz, name, negated);

        case NEG_CONTAINS:
            return getByNameSpecificationContains(clazz, name, negated);
        case NEG_STARTS_WITH:
            return getByNameSpecificationStartsWith(clazz, name, negated);
        case NEG_ENDS_WITH:
            return getByNameSpecificationEndsWith(clazz, name, negated);

        default:
            break;
        }

        throw new UnsupportedOperationException("Received invalid operator: " + op);
    }

    // in between

    // id

    static <T extends IEntity> Specification<T> getByIdSpecification(final Class<T> clazz, final Long id, final boolean negated) {
        if (clazz.equals(BusinessCard.class)) {
            return (Specification<T>) businessCardCardByIdEq(id, negated);
        }
        if (clazz.equals(ClientCard.class)) {
            return (Specification<T>) clientCardCardByIdEq(id, negated);
        }

        return null;
    }

    // name

    static <T extends IEntity> Specification<T> getByNameSpecificationEq(final Class<T> clazz, final String name, final boolean negated) {
        if (clazz.equals(BusinessCard.class)) {
            return (Specification<T>) businessCardByNameEq(name, negated);
        }
        if (clazz.equals(ClientCard.class)) {
            return (Specification<T>) clientCardByNameEq(name, negated);
        }

        return null;
    }

    static <T extends IEntity> Specification<T> getByNameSpecificationContains(final Class<T> clazz, final String name, final boolean negated) {
        if (clazz.equals(BusinessCard.class)) {
            return (Specification<T>) businessCardByNameContains(name, negated);
        }
        if (clazz.equals(ClientCard.class)) {
            return (Specification<T>) clientCardByNameContains(name, negated);
        }

        return null;
    }

    static <T extends IEntity> Specification<T> getByNameSpecificationStartsWith(final Class<T> clazz, final String name, final boolean negated) {
        if (clazz.equals(BusinessCard.class)) {
            return (Specification<T>) businessCardByNameStartsWith(name, negated);
        }
        if (clazz.equals(ClientCard.class)) {
            return (Specification<T>) clientCardByNameStartsWith(name, negated);
        }

        return null;
    }

    static <T extends IEntity> Specification<T> getByNameSpecificationEndsWith(final Class<T> clazz, final String name, final boolean negated) {
        if (clazz.equals(BusinessCard.class)) {
            return (Specification<T>) businessCardByNameEndsWith(name, negated);
        }
        if (clazz.equals(ClientCard.class)) {
            return (Specification<T>) clientCardByNameEndsWith(name, negated);
        }

        return null;
    }

    // Business Card

    private static Specification<BusinessCard> businessCardCardByIdEq(final Long id, final boolean negated) {
        return new Specification<BusinessCard>() {
            @Override
            public final Predicate toPredicate(final Root<BusinessCard> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
                if (negated) {
                    return builder.notEqual(root.get(BusinessCard_.id), id);
                }
                return builder.equal(root.get(BusinessCard_.id), id);
            }
        };
    }

    private static Specification<BusinessCard> businessCardByNameEq(final String name, final boolean negated) {
        return QuerySpecificationSec.<BusinessCard> entityByKeyEq(name, negated, BusinessCard_.name);
    }

    private static Specification<BusinessCard> businessCardByNameContains(final String name, final boolean negated) {
        return QuerySpecificationSec.<BusinessCard> entityByKeyContains(name, negated, BusinessCard_.name);
    }

    private static Specification<BusinessCard> businessCardByNameStartsWith(final String name, final boolean negated) {
        return QuerySpecificationSec.<BusinessCard> entityByKeyStartsWith(name, negated, BusinessCard_.name);
    }

    private static Specification<BusinessCard> businessCardByNameEndsWith(final String name, final boolean negated) {
        return QuerySpecificationSec.<BusinessCard> entityByKeyEndsWith(name, negated, BusinessCard_.name);
    }

    private static Specification<BusinessCard> businessCardByDescriptionEq(final String description, final boolean negated) {
        return QuerySpecificationSec.<BusinessCard> entityByKeyEq(description, negated, BusinessCard_.description);
    }

    private static Specification<BusinessCard> businessCardByDescriptionContains(final String description, final boolean negated) {
        return QuerySpecificationSec.<BusinessCard> entityByKeyContains(description, negated, BusinessCard_.description);
    }

    private static Specification<BusinessCard> businessCardByDescriptionStartsWith(final String description, final boolean negated) {
        return QuerySpecificationSec.<BusinessCard> entityByKeyStartsWith(description, negated, BusinessCard_.description);
    }

    private static Specification<BusinessCard> businessCardByDescriptionEndsWith(final String description, final boolean negated) {
        return QuerySpecificationSec.<BusinessCard> entityByKeyEndsWith(description, negated, BusinessCard_.description);
    }

    // Client Card

    private static Specification<ClientCard> clientCardCardByIdEq(final Long id, final boolean negated) {
        return new Specification<ClientCard>() {
            @Override
            public final Predicate toPredicate(final Root<ClientCard> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
                if (negated) {
                    return builder.notEqual(root.get(ClientCard_.id), id);
                }
                return builder.equal(root.get(ClientCard_.id), id);
            }
        };
    }

    private static Specification<ClientCard> clientCardByNameEq(final String name, final boolean negated) {
        return QuerySpecificationSec.<ClientCard> entityByKeyEq(name, negated, ClientCard_.name);
    }

    private static Specification<ClientCard> clientCardByNameContains(final String name, final boolean negated) {
        return QuerySpecificationSec.<ClientCard> entityByKeyContains(name, negated, ClientCard_.name);
    }

    private static Specification<ClientCard> clientCardByNameStartsWith(final String name, final boolean negated) {
        return QuerySpecificationSec.<ClientCard> entityByKeyStartsWith(name, negated, ClientCard_.name);
    }

    private static Specification<ClientCard> clientCardByNameEndsWith(final String name, final boolean negated) {
        return QuerySpecificationSec.<ClientCard> entityByKeyEndsWith(name, negated, ClientCard_.name);
    }

    private static Specification<ClientCard> clientCardByDescriptionEq(final String description, final boolean negated) {
        return QuerySpecificationSec.<ClientCard> entityByKeyEq(description, negated, ClientCard_.description);
    }

    private static Specification<ClientCard> clientCardByDescriptionContains(final String description, final boolean negated) {
        return QuerySpecificationSec.<ClientCard> entityByKeyContains(description, negated, ClientCard_.description);
    }

    private static Specification<ClientCard> clientCardByDescriptionStartsWith(final String description, final boolean negated) {
        return QuerySpecificationSec.<ClientCard> entityByKeyStartsWith(description, negated, ClientCard_.description);
    }

    private static Specification<ClientCard> clientCardByDescriptionEndsWith(final String description, final boolean negated) {
        return QuerySpecificationSec.<ClientCard> entityByKeyEndsWith(description, negated, ClientCard_.description);
    }

    // generic

    private static <T extends IEntity> Specification<T> entityByKeyEndsWith(final String value, final boolean negated, final SingularAttribute<T, String> metaField) {
        return new Specification<T>() {
            @Override
            public final Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
                if (negated) {
                    return builder.notLike(builder.lower(root.get(metaField)), QueryConstants.ANY_SERVER + value.toLowerCase());
                }
                return builder.like(builder.lower(root.get(metaField)), QueryConstants.ANY_SERVER + value.toLowerCase());
            }
        };
    }

    private static <T extends IEntity> Specification<T> entityByKeyStartsWith(final String value, final boolean negated, final SingularAttribute<T, String> metaField) {
        return new Specification<T>() {
            @Override
            public final Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
                if (negated) {
                    return builder.notLike(builder.lower(root.get(metaField)), value.toLowerCase() + QueryConstants.ANY_SERVER);
                }
                return builder.like(builder.lower(root.get(metaField)), value.toLowerCase() + QueryConstants.ANY_SERVER);
            }
        };
    }

    private static <T extends IEntity> Specification<T> entityByKeyContains(final String value, final boolean negated, final SingularAttribute<T, String> metaField) {
        return new Specification<T>() {
            @Override
            public final Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
                if (negated) {
                    return builder.notLike(builder.lower(root.get(metaField)), QueryConstants.ANY_SERVER + value.toLowerCase() + QueryConstants.ANY_SERVER);
                }
                return builder.like(builder.lower(root.get(metaField)), QueryConstants.ANY_SERVER + value.toLowerCase() + QueryConstants.ANY_SERVER);
            }
        };
    }

    private static <T extends IEntity> Specification<T> entityByKeyEq(final String value, final boolean negated, final SingularAttribute<T, String> metaField) {
        return new Specification<T>() {
            @Override
            public final Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
                if (negated) {
                    return builder.notEqual(builder.lower(root.get(metaField)), value.toLowerCase());
                }
                return builder.equal(builder.lower(root.get(metaField)), value.toLowerCase());
            }
        };
    }

}
