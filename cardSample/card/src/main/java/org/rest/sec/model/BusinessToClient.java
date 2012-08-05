package org.rest.sec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.rest.common.persistence.model.INameableEntity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@Entity
@XmlRootElement
@XStreamAlias("role")
public class BusinessToClient implements INameableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    @XStreamAsAttribute
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    // @formatter:off
    @OneToOne( /* cascade = { CascadeType.REMOVE }, */fetch = FetchType.EAGER)
    private BusinessCard businessCard;
    // @formatter:on

    public BusinessToClient() {
        super();
    }

    public BusinessToClient(final String nameToSet) {
        super();
        name = nameToSet;
    }

    public BusinessToClient(final String nameToSet, final BusinessCard businessCardToSet) {
        super();

        name = nameToSet;
        businessCard = businessCardToSet;
    }

    // API

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long idToSet) {
        id = idToSet;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String nameToSet) {
        name = nameToSet;
    }

    public BusinessCard getBusinessCard() {
        return businessCard;
    }

    public void setBusinessCard(final BusinessCard businessCardToSet) {
        businessCard = businessCardToSet;
    }

    //

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final BusinessToClient other = (BusinessToClient) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).toString();
    }

}
