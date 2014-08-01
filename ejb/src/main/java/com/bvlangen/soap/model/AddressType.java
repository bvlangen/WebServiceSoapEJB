package com.bvlangen.soap.model;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AddressType")
public enum AddressType {
    UNDEFINED,
    HOME,
    CORRESPONDENCE
}
