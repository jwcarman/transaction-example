package com.gafri.example.transaction.domain.entity;

import org.domdrides.entity.UuidEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Policy extends UuidEntity
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private String policyNumber;

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    @Basic
    public String getPolicyNumber()
    {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber)
    {
        this.policyNumber = policyNumber;
    }
}
