package com.bvlangen.soap.model;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(name = "EmailPrimaryKey")
public class EmailPK implements Serializable {

    @Column(name="customer_id")
    private int customerId;
    private int type;

    public EmailPK() {}

    public int getCustomerId() {
        return customerId;
    }

    @XmlElement(name = "CustomerID")
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name = "EmailType")
    public void setType(EmailType type) {
        this.type = type.ordinal();
    }

    public EmailType getType() {
        return EmailType.values()[type];
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof EmailPK) &&
                customerId == ((EmailPK)o).customerId &&
                type == ((EmailPK)o).type;
    }

    @Override
    public int hashCode() {
        return customerId * EmailType.values().length + type;
    }
}
