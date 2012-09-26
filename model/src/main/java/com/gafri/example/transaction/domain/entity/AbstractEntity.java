package com.gafri.example.transaction.domain.entity;

import org.domdrides.entity.Entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Entity<String>
{
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    @Id
    private String id = UUID.randomUUID().toString();

//----------------------------------------------------------------------------------------------------------------------
// Entity Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id.equals(that.id);
    }

    public String getId()
    {
        return id;
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    protected void setId(String id)
    {
        this.id = id;
    }
}
