package org.rest.sec.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BusinessToClient.class)
public abstract class BusinessToClient_ {

    public static volatile SingularAttribute<BusinessToClient, Long> id;
    public static volatile SingularAttribute<BusinessToClient, String> name;
    public static volatile SetAttribute<BusinessToClient, BusinessCard> privileges;

}
