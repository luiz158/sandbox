package org.rest.sec.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BusinessCard.class)
public abstract class BusinessCard_ {

    public static volatile SingularAttribute<BusinessCard, Long> id;
    public static volatile SingularAttribute<BusinessCard, String> name;
    public static volatile SingularAttribute<BusinessCard, String> lastName;
    public static volatile SingularAttribute<BusinessCard, String> company;

}
