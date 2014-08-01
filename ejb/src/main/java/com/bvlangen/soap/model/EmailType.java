package com.bvlangen.soap.model;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "EmailType")
public enum EmailType {
        UNDEFINED,
        HOME,
        WORK
}
