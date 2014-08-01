package com.bvlangen.soap.model;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(name = "AddressPrimaryKey")
public class AddressPK implements Serializable {

    @Column(name = "customer_id")
    private int customerId;
    private int type;

    public AddressPK() {}

    @XmlElement(name = "CustomerID")
    public void setCustomerId(int id) {
        this.customerId = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    @XmlElement(name = "AddressType")
    public void setType(AddressType type) {
        this.type = type.ordinal();
    }

    public AddressType getType() {
        return AddressType.values()[type];
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof AddressPK) &&
                customerId == ((AddressPK)o).customerId &&
                type == ((AddressPK)o).type;
    }

    @Override
    public int hashCode() {
        return customerId * AddressType.values().length + type;
    }
}
