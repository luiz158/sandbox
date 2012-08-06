package org.rest.sec.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.rest.common.persistence.model.INameableEntity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@XmlRootElement
@XStreamAlias("businessCard")
public class BusinessCard implements INameableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BC_ID")
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = true)
    private String lastName;
    @Column(unique = false, nullable = true)
    private String company;

    //@formatter:off
    @ManyToMany( /*cascade = { CascadeType.REMOVE },*/fetch = FetchType.EAGER )
    @JoinTable( joinColumns = { @JoinColumn( name = "BC_ID",referencedColumnName = "BC_ID" ) },inverseJoinColumns = { @JoinColumn( name = "CC_ID",referencedColumnName = "CC_ID" ) } )
    private Set<ClientCard> clientCards;
    //@formatter:on

    public BusinessCard() {
        super();
    }

    public BusinessCard(final String nameToSet) {
        super();
        name = nameToSet;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastNameToSet) {
        this.lastName = lastNameToSet;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(final String companyToSet) {
        company = companyToSet;
    }

    public Set<ClientCard> getClientCards() {
        return clientCards;
    }

    public void setClientCards(Set<ClientCard> clientCardsToSet) {
        this.clientCards = clientCardsToSet;
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
        final BusinessCard other = (BusinessCard) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

}
