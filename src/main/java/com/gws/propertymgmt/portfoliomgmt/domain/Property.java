/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.gws.productionenvy.framework.AggregateRootObject;
import com.gws.productionenvy.framework.DomainEntryPointException;
import com.gws.productionenvy.framework.DomainInvocationOutcome;

/**
 * Aggregate Root Interface.
 * 
 * <p>All methods an aggregate root interface can only declare void as the return type.</p>
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public interface Property extends AggregateRootObject
{
    /**
     * register house to be managed.
     * 
     * @return DomainInvocationOutcome outcome of invocation.
     * 
     * @throws com.gws.productionenvy.framework.DomainEntryPointException
     *     when the invocation of domain behavior results in any outcome
     *     other than the expected path, in this case the property is
     *     successfully registered.
     */
    DomainInvocationOutcome register() throws DomainEntryPointException;
}
