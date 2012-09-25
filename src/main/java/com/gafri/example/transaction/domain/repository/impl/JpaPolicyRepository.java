package com.gafri.example.transaction.domain.repository.impl;

import com.gafri.example.transaction.domain.entity.Policy;
import com.gafri.example.transaction.domain.repository.PolicyRepository;

public class JpaPolicyRepository extends AbstractJpaRepository<Policy,String> implements PolicyRepository
{
//----------------------------------------------------------------------------------------------------------------------
// Constructors
//----------------------------------------------------------------------------------------------------------------------

    public JpaPolicyRepository()
    {
        super(Policy.class);
    }
}
